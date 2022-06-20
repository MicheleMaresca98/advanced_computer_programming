package server;

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

import org.apache.activemq.spring.ActiveMQConnectionFactory;



public class ComputeUnit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.REQ", "REQ");
		prop.put("queue.RPLSUM", "RPLSUM");
		prop.put("queue.RPLMUL", "RPLMUL");
		
		try {
			Context ctx = new InitialContext(prop);
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			
			((ActiveMQConnectionFactory) qconnf).setTrustAllPackages(true);
			QueueConnection qconn = qconnf.createQueueConnection();
			qconn.start();
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue req = (Queue) ctx.lookup("REQ");
			
			QueueReceiver receiver = qsession.createReceiver(req);
			Compute compute = new Compute();
			ComputeListener listener = new ComputeListener(qconn, compute);
			receiver.setMessageListener(listener);
			
			System.out.println("[ComputeUnit]: avviato");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
