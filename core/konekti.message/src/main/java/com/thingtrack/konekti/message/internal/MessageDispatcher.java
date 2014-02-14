package com.thingtrack.konekti.message.internal;

import javax.annotation.PostConstruct;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageDispatcher implements MessageListener {

	private ConnectionFactory connectionFactory;
	private Session session;
	
	private String requestQueue = "IN_QUEUE";
	private MessageConsumer consumer;
	
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        
    }
    
	@SuppressWarnings("unused")
	@PostConstruct
	private void createUsersQueues() throws Exception {
		// create message unique request queue for all users
		Connection connection = connectionFactory.createConnection();
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination requestUserQueue = session.createQueue(requestQueue);
		consumer = session.createConsumer(requestUserQueue);
		consumer.setMessageListener(this);
		
	}
		
	@Override
	public void onMessage(Message message) {
		try {
			Integer organizationIDTo = (Integer) message.getObjectProperty("ORGANIZATION_ID_TO");
			Integer locationIdTo = (Integer) message.getObjectProperty("LOCATION_ID_TO");
			Integer areaIdTo = (Integer) message.getObjectProperty("AREA_ID_TO");
			Integer userIdTo = (Integer) message.getObjectProperty("USER_ID_TO");
			
			TextMessage response = this.session.createTextMessage();
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String payload = txtMessage.getText();
				
				response.setText(payload);
				if (userIdTo != null) {
					// send the message to a particular user
					Destination responseUserQueue = session.createQueue(userIdTo.toString());
					MessageProducer producer = session.createProducer(responseUserQueue);
					
					producer.send(message);
				}
				else {
					if (areaIdTo != null) {
						// send message to all users of the area
					}
					else {
						if (locationIdTo != null) {
							// send message to all users of the location
						}
						else {
							// send message to all users of the organization
						}
					}
				}
					
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
