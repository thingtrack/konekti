package com.thingtrack.konekti.domain.mobile.config;

public class MessageConfigure extends Configure {
	public MessageConfigure(boolean active, String deviceName, String mac, String host, int port, int keepAlive, int qualityOfService, String topic) {
		super(active, deviceName, mac, host, port, keepAlive, qualityOfService, topic);
		
	}
}
