package magazzino;

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

import coda.CodaCircolare;
import coda.CodaWrapperLock;

public class Magazzino {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int size = 10; // dimensione coda
		
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.Richiesta", "Richiesta");
		
		try {
			Context ctx = new InitialContext(prop);
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			Queue richiesta = (Queue) ctx.lookup("Richiesta");
			
			
			QueueConnection qconn = qconnf.createQueueConnection();
			qconn.start();
			
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = qsession.createReceiver(richiesta);
			
			CodaWrapperLock coda = new CodaWrapperLock(new CodaCircolare(size));
			
			MagazzinoListener listener = new MagazzinoListener(coda, qconn);
			receiver.setMessageListener(listener);
			
			System.out.println("[Magazzino] avviato");
					
					
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
