package controlstation;

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

public class ControlStation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 20;
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("topic.richiestesensori", "richiestesensori");
		try {
			Context ctx = new InitialContext(prop);
			TopicConnectionFactory tconnf = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
			TopicConnection tconn = tconnf.createTopicConnection();
			TopicSession tsession = tconn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic richiestesensori = (Topic) ctx.lookup("richiestesensori");
			TopicPublisher publisher = tsession.createPublisher(richiestesensori);
			for(int i=0; i<N; i++) {
				Thread.sleep(1000);
				TextMessage message = tsession.createTextMessage();
				int ran = (int)(Math.random()*3);
				String comando = null;
				if(ran == 0) {
					comando = new String("startSensor");
					message.setText(comando);
				}else if(ran == 1) {
					comando = new String("stopSensor");
					message.setText(comando);
				}else {
					comando = new String("read");
					message.setText(comando);
				}
				publisher.publish(message);
				System.out.println("[ControlStation]: ha pubblicato il comando " + comando);
				
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
