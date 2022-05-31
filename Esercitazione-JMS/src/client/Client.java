package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 10;
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.Richiesta", "Richiesta");
		prop.put("queue.Risposta", "Risposta");
		
		try {
			Context ctx = new InitialContext(prop);
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			Queue richiesta = (Queue) ctx.lookup("Richiesta");
			Queue risposta = (Queue) ctx.lookup("Risposta");
			
			QueueConnection qconn = qconnf.createQueueConnection();
			qconn.start();
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueReceiver receiver = qsession.createReceiver(risposta);
			ClientListener listener = new ClientListener();
			receiver.setMessageListener(listener);
			
			QueueSender sender = qsession.createSender(richiesta);
			MapMessage message = qsession.createMapMessage();
			
			for(int i=0; i<N; i++) {
				if(Math.random() < 0.5) {
					message.setString("operazione", "deposita");
					int value = (int)(Math.random()*100);
					message.setInt("valore", value);
					message.setJMSReplyTo(risposta);
					sender.send(message);
					
				}else {
					message.setString("operazione", "preleva");
					message.setJMSReplyTo(risposta);
					sender.send(message);
				}
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
