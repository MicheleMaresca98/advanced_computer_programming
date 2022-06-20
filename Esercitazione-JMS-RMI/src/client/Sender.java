package client;

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
	
	public void run() {
		int N = 10;
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.Richiesta", "Richiesta");
		try {
			Context ctx = new InitialContext(prop);
			Queue richiesta = (Queue) ctx.lookup("Richiesta");
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			QueueSession qsession = (QueueSession) qconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = qsession.createSender(richiesta);
			for(int i=0; i<N; i++) {
				MapMessage message = qsession.createMapMessage();
				if(i%2 == 0) {
					String tiporichiesta = "deposita";
					int id_articolo = (int)(Math.random()*100);
					message.setString("tiporichiesta", tiporichiesta);
					message.setInt("id_articolo", id_articolo);
					System.out.println("[Sender]: inviato deposita id_articolo " + id_articolo);

				}else {
					String tiporichiesta = "preleva";
					message.setString("tiporichiesta", tiporichiesta);
					System.out.println("[Client-Sender]: inviato preleva");
				}
				sender.send(message);
			}
			sender.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
