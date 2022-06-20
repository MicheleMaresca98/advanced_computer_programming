package sensor;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import coda.CodaCircolare;
import coda.CodaWrapperLock;

public class Sensor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int D = 5;
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("topic.richiestesensori", "richiestesensori");
		try {
			Context ctx = new InitialContext(prop);
			TopicConnectionFactory tconnf = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
			TopicConnection tconn = tconnf.createTopicConnection();
			tconn.start();
			TopicSession tsession = tconn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic richiestesensori = (Topic) ctx.lookup("richiestesensori");
			TopicSubscriber subscriber = tsession.createSubscriber(richiestesensori);
			CodaWrapperLock coda = new CodaWrapperLock(new CodaCircolare(D));
			SensorListener listener = new SensorListener(coda);
			subscriber.setMessageListener(listener);
			
			System.out.println("[Sensor]: avviato");
			
			String pathfile = args[0];
			TExecutor tExecutor = new TExecutor(coda, pathfile);
			tExecutor.start();
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	}

}
