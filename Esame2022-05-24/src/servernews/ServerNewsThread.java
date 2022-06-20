package servernews;

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

public class ServerNewsThread extends Thread {
	
	private String notizia;
	private String tipo;
	
	public ServerNewsThread(String notizia, String tipo) {
		this.notizia = notizia;
		this.tipo = tipo;
	}
	
	public void run() {
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("topic.Sport", "Sport");
		prop.put("topic.Finance", "Finance");
		
		try {
			Context ctx = new InitialContext(prop);
			TopicConnectionFactory tconnf = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
			TopicConnection tconn = tconnf.createTopicConnection();
			TopicSession tsession = tconn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			if(tipo.equals("sport")) {
				Topic sport = (Topic) ctx.lookup("Sport");
				TopicPublisher publisher = tsession.createPublisher(sport);
				TextMessage message = tsession.createTextMessage();
				message.setText(notizia);
				publisher.send(message);
				System.out.println("[ServerNewsThread]: pubblicato notizia " + notizia + " su topic " + tipo);
				publisher.close();
				tsession.close();
				tconn.close();
			}else if(tipo.equals("finance")) {
				Topic finance = (Topic) ctx.lookup("Finance");
				TopicPublisher publisher = tsession.createPublisher(finance);
				TextMessage message = tsession.createTextMessage();
				message.setText(notizia);
				publisher.send(message);
				System.out.println("[ServerNewsThread]: pubblicato notizia " + notizia + " su topic " + tipo);
				publisher.close();
				tsession.close();
				tconn.close();
			}else {
				System.out.println("[ServerNewsThread]: tipo invalido");
			}
				
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
