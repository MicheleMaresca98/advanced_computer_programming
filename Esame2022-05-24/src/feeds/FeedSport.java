package feeds;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FeedSport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("topic.Sport", "Sport");
		
		try {
			Context ctx = new InitialContext(prop);
			TopicConnectionFactory tconnf = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
			TopicConnection tconn = tconnf.createTopicConnection();
			tconn.start();
			TopicSession tsession = tconn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic sport = (Topic) ctx.lookup("Sport");
			TopicSubscriber subscriber = tsession.createSubscriber(sport);
			SportListener listener = new SportListener();
			subscriber.setMessageListener(listener);
			System.out.println("[FeedSport]: avviato");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
