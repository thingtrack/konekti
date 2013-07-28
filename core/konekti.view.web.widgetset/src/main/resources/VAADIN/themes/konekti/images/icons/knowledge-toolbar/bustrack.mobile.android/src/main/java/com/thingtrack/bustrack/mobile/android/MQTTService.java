/**
 * 
 */
package com.thingtrack.bustrack.mobile.android;

import com.ibm.mqtt.MqttSimpleCallback;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.util.Log;

import com.ibm.mqtt.IMqttClient;
import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;
import com.ibm.mqtt.MqttNotConnectedException;
import com.ibm.mqtt.MqttPersistence;
import com.ibm.mqtt.MqttPersistenceException;
import com.ibm.mqtt.MqttSimpleCallback;

/**
 * @author thk01
 *
 */

/*
 * Implement an MQTT client in Android, able to receive
 *  push notifications from an MQTT message broker server.
 *  
 */
public class MQTTService extends Service implements MqttSimpleCallback {

	/* (non-Javadoc)
	 * @see com.ibm.mqtt.MqttSimpleCallback#connectionLost()
	 */
	
	/************************************************************************/
    /*    CONSTANTS                                                         */
    /************************************************************************/

    // something unique to identify your app - used for stuff like accessing
    //   application preferences
    public static final String APP_ID = "com.dalelane.mqtt";

    // constants used to notify the Activity UI of received messages
    public static final String MQTT_MSG_RECEIVED_INTENT = "com.dalelane.mqtt.MSGRECVD";
    public static final String MQTT_MSG_RECEIVED_TOPIC  = "com.dalelane.mqtt.MSGRECVD_TOPIC";
    public static final String MQTT_MSG_RECEIVED_MSG    = "com.dalelane.mqtt.MSGRECVD_MSGBODY";

    // constants used to tell the Activity UI the connection status
    public static final String MQTT_STATUS_INTENT = "com.dalelane.mqtt.STATUS";
    public static final String MQTT_STATUS_MSG    = "com.dalelane.mqtt.STATUS_MSG";

    // constant used internally to schedule the next ping event
    public static final String MQTT_PING_ACTION = "com.dalelane.mqtt.PING";

    // constants used by status bar notifications
    public static final int MQTT_NOTIFICATION_ONGOING = 1;
    public static final int MQTT_NOTIFICATION_UPDATE  = 2;
    
 // constants used to define MQTT connection status
    public enum MQTTConnectionStatus
    {
        INITIAL,                            // initial status
        CONNECTING,                         // attempting to connect
        CONNECTED,                          // connected
        NOTCONNECTED_WAITINGFORINTERNET,    // can't connect because the phone
                                            //     does not have Internet access
        NOTCONNECTED_USERDISCONNECT,        // user has explicitly requested
                                            //     disconnection
        NOTCONNECTED_DATADISABLED,          // can't connect because the user
                                            //     has disabled data access
        NOTCONNECTED_UNKNOWNREASON          // failed to connect for some reason
    }

    // MQTT constants
    public static final int MAX_MQTT_CLIENTID_LENGTH = 22;
   
    
    /// MAINTAIN
    /************************************************************************/
    /*    VARIABLES used to maintain state                                  */
    /************************************************************************/
    // status of MQTT client connection
    private MQTTConnectionStatus connectionStatus = MQTTConnectionStatus.INITIAL;
    
    
    // MQTT connection
    /************************************************************************/
    /*    VARIABLES used to configure MQTT connection                       */
    /************************************************************************/

    // taken from preferences
    //    host name of the server we're receiving push notifications from
    private String          brokerHostName       = "";
    // taken from preferences
    //    topic we want to receive messages about
    //    can include wildcards - e.g.  '#' matches anything
    private String          topicName            = "";    

    // defaults - this sample uses very basic defaults for it's interactions
    //   with message brokers
    private int             brokerPortNumber     = 1883;
    private MqttPersistence usePersistence       = null;
    private boolean         cleanStart           = false;
    private int[]           qualitiesOfService   = { 0 } ;

    //  how often should the app ping the server to keep the connection alive?
    //
    //   too frequently - and you waste battery life
    //   too infrequently - and you wont notice if you lose your connection
    //                       until the next unsuccessfull attempt to ping
    //
    //   it's a trade-off between how time-sensitive the data is that your
    //      app is handling, vs the acceptable impact on battery life
    //
    //   it is perhaps also worth bearing in mind the network's support for
    //     long running, idle connections. Ideally, to keep a connection open
    //     you want to use a keep alive value that is less than the period of
    //     time after which a network operator will kill an idle connection
    private short           keepAliveSeconds     = 20 * 60; 

    // This is how the Android client app will identify itself to the
    //  message broker.
    // It has to be unique to the broker - two clients are not permitted to
    //  connect to the same broker using the same client ID.
    private String          mqttClientId = null; 
    
    // Other
    /************************************************************************/
    /*    VARIABLES  - other local variables                                */
    /************************************************************************/
    // connection to the message broker
    private IMqttClient mqttClient = null;

    // receiver that notifies the Service when the phone gets data connection
    private NetworkConnectionIntentReceiver netConnReceiver;

    // receiver that notifies the Service when the user changes data use preferences
    private BackgroundDataChangeIntentReceiver dataEnabledReceiver;

    // receiver that wakes the Service up when it's time to ping the server
    private PingSender pingSender;

    /************************************************************************/
    /*    METHODS - core Service lifecycle methods                          */
    /************************************************************************/
	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		// reset status variable to initial state
        connectionStatus = MQTTConnectionStatus.INITIAL;

        // create a binder that will let the Activity UI send
        //   commands to the Service
        mBinder = new LocalBinder<MQTTService>(this);

        // get the broker settings out of app preferences
        //   this is not the only way to do this - for example, you could use
        //   the Intent that starts the Service to pass on configuration values
        SharedPreferences settings = getSharedPreferences(APP_ID, MODE_PRIVATE);
        brokerHostName = settings.getString("broker", "");
        topicName      = settings.getString("topic",  "");

        // register to be notified whenever the user changes their preferences
        //  relating to background data use - so that we can respect the current
        //  preference
        dataEnabledReceiver = new BackgroundDataChangeIntentReceiver();
        registerReceiver(dataEnabledReceiver,
                         new IntentFilter(ConnectivityManager.ACTION_BACKGROUND_DATA_SETTING_CHANGED));

        // define the connection to the broker
        defineConnectionToBroker(brokerHostName);
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub

		//----super.onStart(intent, startId);
		   // This is the old onStart method that will be called on the pre-2.0
        // platform.  On 2.0 or later we override onStartCommand() so this
        // method will not be called.        

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                handleStart(intent, startId);
//            }
//        }, "MQTTservice").start();
//	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

    


	@Override
	public void connectionLost() throws Exception {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.ibm.mqtt.MqttSimpleCallback#publishArrived(java.lang.String, byte[], int, boolean)
	 */
	@Override
	public void publishArrived(String arg0, byte[] arg1, int arg2, boolean arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}  // para ok MQTTServ



}
