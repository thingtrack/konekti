/**
 * 
 */
package com.thingtrack.bustrack.mobile.android;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author thk01
 *
 */
public class AutoStartNotifyReceiver extends BroadcastReceiver {
	private final String BOOT_COMPLETED_ACTION = "android.intent.action.BOOT_COMPLETED";
	
	

	 @Override
	 public void onReceive(Context context, Intent intent) {
	  // TODO Auto-generated method stub

	 
	  if(intent.getAction().equals(BOOT_COMPLETED_ACTION)){
			 Intent i = new Intent(context, GPSservice.class);
			 context.startService(i);
			 Log.d("AutoStartNotifyReceiver", "onReceived");		  
		    

	  }

	 }
}
