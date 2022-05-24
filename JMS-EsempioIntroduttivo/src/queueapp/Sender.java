package queueapp;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Sender {

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
			
			QueueConnection queueConn = queueConnectionFactory.createQueueConnection();
			QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueSender sender = queueSession.createSender(queue);
			TextMessage message = queueSession.createTextMessage();
			
			for(int i=0; i<5; i++) {
				message.setText("hello_" + i);
				sender.send(message);
			}
			
			message.setText("fine");
			sender.send(message);
			
			System.out.println("I messaggi sono stati inviati!");
			
			sender.close();
			queueSession.close();
			queueConn.close();
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
