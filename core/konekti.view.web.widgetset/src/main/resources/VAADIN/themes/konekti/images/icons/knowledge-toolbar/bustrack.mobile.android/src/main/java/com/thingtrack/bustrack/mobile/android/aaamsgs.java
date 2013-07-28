//private StatusUpdateReceiver statusUpdateIntentReceiver;
//private MQTTMessageReceiver  messageIntentReceiver;  
//
//@Override
//public void onCreate(Bundle savedInstanceState)
//{
//    ...  
//
//    statusUpdateIntentReceiver = new StatusUpdateReceiver();
//    IntentFilter intentSFilter = new IntentFilter(MQTTService.MQTT_STATUS_INTENT);
//    registerReceiver(statusUpdateIntentReceiver, intentSFilter);  
//
//    messageIntentReceiver = new MQTTMessageReceiver();
//    IntentFilter intentCFilter = new IntentFilter(MQTTService.MQTT_MSG_RECEIVED_INTENT);
//    registerReceiver(messageIntentReceiver, intentCFilter);  
//
//    ...
//}  
//
//public class StatusUpdateReceiver extends BroadcastReceiver
//{
//    @Override
//    public void onReceive(Context context, Intent intent)
//    {
//        Bundle notificationData = intent.getExtras();
//        String newStatus = notificationData.getString(MQTTService.MQTT_STATUS_MSG);	    	  
//
//        ...
//    }
//}
//public class MQTTMessageReceiver extends BroadcastReceiver
//{
//    @Override
//    public void onReceive(Context context, Intent intent)
//    {
//        Bundle notificationData = intent.getExtras();
//        String newTopic = notificationData.getString(MQTTService.MQTT_MSG_RECEIVED_TOPIC);
//        String newData  = notificationData.getString(MQTTService.MQTT_MSG_RECEIVED_MSG);	    	  
//
//        ...
//    }
//}  
//
//@Override
//protected void onDestroy()
//{
//    ...  
//
//    unregisterReceiver(statusUpdateIntentReceiver);
//    unregisterReceiver(messageIntentReceiver);  
//
//    ...
//} 