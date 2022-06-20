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

public class Sensore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int N = 5;
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.valorisensori", "valorisensori");
		
		try {
			Context ctx = new InitialContext(prop);
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			Queue valorisensori = (Queue) ctx.lookup("valorisensori");
			QueueConnection qconn = qconnf.createQueueConnection();
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = qsession.createSender(valorisensori);
			for(int i=0; i<N; i++) {
				MapMessage message = qsession.createMapMessage();
				int temperatura = (int)(Math.random()*100);
				message.setInt("temperatura", temperatura);
				sender.send(message);
				System.out.println("[Sensore] ha inviato temperatura " + temperatura);
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
