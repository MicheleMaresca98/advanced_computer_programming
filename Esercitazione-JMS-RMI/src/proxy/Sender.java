package proxy;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sender extends Thread{
	
	private int id_articolo;
	
	public Sender(int id_articolo) {
		this.id_articolo = id_articolo;
	}
	
	public void run() {
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.Risposta", "Risposta");
		try {
			Context ctx = new InitialContext(prop);
			Queue richiesta = (Queue) ctx.lookup("Risposta");
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			QueueSession qsession = (QueueSession) qconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = qsession.createSender(richiesta);
			
			MapMessage message = qsession.createMapMessage();
				
			message.setInt("id_articolo", id_articolo);
			System.out.println("[Sender]: inviato deposita id_articolo " + id_articolo);

			sender.send(message);
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
