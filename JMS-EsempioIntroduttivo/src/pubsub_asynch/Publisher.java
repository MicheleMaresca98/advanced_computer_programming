package pubsub_asynch;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Publisher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		prop.put("topic.soccer", "soccernews");
		prop.put("topic.tennis", "tennisnews");
		
		try {
			Context jndiContext = new InitialContext(prop);
			TopicConnectionFactory tcf = (TopicConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
			TopicConnection tc = tcf.createTopicConnection();
			Topic soccer = (Topic)jndiContext.lookup("soccer");
			Topic tennis = (Topic)jndiContext.lookup("tennis");

			TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic selectedTopic; 
			if(args[0].equalsIgnoreCase("soccer")) 
				selectedTopic = soccer;
			else if(args[0].equalsIgnoreCase("tennis")) 
				selectedTopic = tennis;
			else { 
				System.out.println("unknown topic");
				return;
			}
			
			TopicPublisher publisher = ts.createPublisher(selectedTopic);
			TextMessage message = ts.createTextMessage();
			message.setText(args[1]);
			message.setIntProperty("propInt", 10); // can be used by possible message selectors by subscribers  
			publisher.publish(message);
			
			System.out.println("I've published the message " + args[1]);

			//Some attributes of the message
			System.out.println("\nMessage id " + message.getJMSMessageID());
			System.out.println("Message id " + message.getJMSCorrelationID()); // not set
			System.out.println("Message id " + message.getJMSDeliveryMode()); //PERSISTENT, NON-PERSISTENT
			System.out.println("Message id " + message.getJMSExpiration());
			System.out.println("Message id " + message.getJMSPriority());
			System.out.println("Message id " + message.getJMSReplyTo());
			
			publisher.close();
			ts.close();
			tc.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
