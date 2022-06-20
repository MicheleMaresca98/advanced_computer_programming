package proxy;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import interfaces.IServer;

public class Receiver extends Thread {
	
	private IServer server;
	
	public Receiver(IServer server) {
		this.server = server;
	}
	
	public void run() {
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.Richiesta", "Richiesta");
		try {
			Context ctx = new InitialContext(prop);
			Queue risposta = (Queue) ctx.lookup("Richiesta");
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			qconn.start();
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = qsession.createReceiver(risposta);
			ProxyListener listener = new ProxyListener(server);
			receiver.setMessageListener(listener);
			
			System.out.println("[Proxy-Receiver]: avviato");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
