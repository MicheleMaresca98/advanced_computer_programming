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

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dato = Integer.valueOf(args[0]).intValue();
		int portaLogger = Integer.valueOf(args[1]).intValue();
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.storage", "storage");
		
		try {
			Context ctx = new InitialContext(prop);
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			Queue storage = (Queue) ctx.lookup("storage");
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = qsession.createSender(storage);
			MapMessage message = qsession.createMapMessage();
			
			message.setInt("dato", dato);
			message.setInt("portaLogger", portaLogger);
			sender.send(message);
			System.out.println("[Client]: il sender ha inviato il dato " + dato + " al logger su porta " + portaLogger);
			sender.close();
			qsession.close();
			qconn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
