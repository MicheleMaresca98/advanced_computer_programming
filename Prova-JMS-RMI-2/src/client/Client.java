package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String nomePrinter = args[0];
		int N = 5;
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("topic.PrintRequest", "PrintRequest");
		
		try {
			Context ctx = new InitialContext(prop);
			TopicConnectionFactory tconnf = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
			Topic printRequest = (Topic) ctx.lookup("PrintRequest");
			TopicConnection tconn = tconnf.createTopicConnection();
			TopicSession tsession = tconn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher publisher = tsession.createPublisher(printRequest);
			for(int i=0; i<N; i++) {
				MapMessage message = tsession.createMapMessage();
				String nomeDocumento = new String("doc" + (int)(Math.random()*41));
				message.setString("nomeDocumento", nomeDocumento);
				message.setString("nomePrinter", nomePrinter);
				publisher.send(message);
				System.out.println("[Client]: ha inviato in stampa il documento " + nomeDocumento);
			}
			publisher.close();
			tsession.close();
			tconn.close();
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
