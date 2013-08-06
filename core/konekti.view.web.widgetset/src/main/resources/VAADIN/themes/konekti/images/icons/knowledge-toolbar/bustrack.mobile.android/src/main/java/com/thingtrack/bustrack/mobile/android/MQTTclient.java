/**
 * 
 */
package com.thingtrack.bustrack.mobile.android;

import com.ibm.mqtt.MqttSimpleCallback;

/**
 * @author thk01
 *
 */
public class MQTTclient implements MqttSimpleCallback {

	/* (non-Javadoc)
	 * @see com.ibm.mqtt.MqttSimpleCallback#connectionLost()
	 */
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

}
