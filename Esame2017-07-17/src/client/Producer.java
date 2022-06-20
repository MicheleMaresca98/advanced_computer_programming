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


public class Producer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int N = 5;
			Hashtable<String, String> prop = new Hashtable<String, String>();
			prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
			prop.put("queue.richiestaoperazione", "richiestaoperazione");
			prop.put("queue.rispostaoperazione", "rispostaoperazione");

			Context ctx = new InitialContext(prop);
			Queue richiestaoperazione = (Queue) ctx.lookup("richiestaoperazione");
			Queue rispostaoperazione = (Queue) ctx.lookup("rispostaoperazione");
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			qconn.start();
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = qsession.createSender(richiestaoperazione);
			for(int i=0; i<N; i++) {
				MapMessage message = qsession.createMapMessage();
				String operazione;
				if(Math.random() < 0.5) {
					operazione = "add";
					message.setString("operazione", operazione);
				}else {
					operazione = "mul";
					message.setString("operazione", operazione);
				}
				int i1 = (int)(Math.random()*100);
				int i2 = (int)(Math.random()*100);
				message.setInt("i1", i1);
				message.setInt("i2", i2);
				message.setJMSReplyTo(rispostaoperazione);
				sender.send(message);
				System.out.println("[Producer] ha inviato " + operazione + " i1 = " + i1 + " i2 = " + i2);
			}
			
			QueueReceiver receiver = qsession.createReceiver(rispostaoperazione);
			MyListener listener = new MyListener();
			receiver.setMessageListener(listener);
			 
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
