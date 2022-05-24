package pubsubapp;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Subscriber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("topic.test", "mytopictest");
		
		try {
			
			Context jndiContext = new InitialContext(prop);
			
			TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
			Topic topic = (Topic)jndiContext.lookup("test");
			
			TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
			topicConnection.start();
			TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TopicSubscriber subscriber = topicSession.createSubscriber(topic);
			TextMessage message = topicSession.createTextMessage();
			
			do {
				System.out.println("In attesa di messaggi!");
				message = (TextMessage)subscriber.receive();
				System.out.println(" + messaggio ricevuto: " + message.getText());
			}while(!((message.getText()).equals("fine")));
			
			subscriber.close();
			topicSession.close();
			topicConnection.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
