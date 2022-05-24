package queueapp;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.test", "myqueue");
		
		try {
			Context jndiContext = new InitialContext(prop);
			QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory)jndiContext.lookup("QueueConnectionFactory");
			Queue queue = (Queue)jndiContext.lookup("test");
			
			QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
			queueConnection.start();
			
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueReceiver receiver = queueSession.createReceiver(queue);
			
			TextMessage message = queueSession.createTextMessage();
			
			
			
			do {
				System.out.println("In attesa di messaggi!");
				message = (TextMessage) receiver.receive();
				System.out.println(" + messaggio ricevuto: " + message.getText());
			}while(!((message.getText()).equals("fine")));
			
			receiver.close();
			queueSession.close();
			queueConnection.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
