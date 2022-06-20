package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.Queue;


public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nmessaggi = 5;
		String nomePrinter = args[0];
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.PrintRequest", "PrintRequest");
		
		try {
			Context ctx = new InitialContext(prop);
			Queue printRequest = (Queue) ctx.lookup("PrintRequest");
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = qsession.createSender(printRequest);
			
			for(int i=0; i<nmessaggi; i++) {
				String nomeDocumento = new String("doc" + (int)(Math.random()*41));
				MapMessage message = qsession.createMapMessage();
				message.setString("nomeDocumento", nomeDocumento);
				message.setString("nomePrinter", nomePrinter);
				
				sender.send(message);
				System.out.println("[Client]: ha inviato stampa nomeDocumento " + nomeDocumento);
			}
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
