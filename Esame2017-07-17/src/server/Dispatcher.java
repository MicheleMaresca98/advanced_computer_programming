package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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

import interfaces.ICalcolatrice;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Hashtable<String, String> prop = new Hashtable<String, String>();
			prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
			prop.put("queue.richiestaoperazione", "richiestaoperazione");
			Context ctx = new InitialContext(prop);
			Queue richiestaoperazione = (Queue) ctx.lookup("richiestaoperazione");
			QueueConnectionFactory qconnf = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			qconn.start();
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = qsession.createReceiver(richiestaoperazione);
			Registry rmiRegistry = LocateRegistry.getRegistry();
			ICalcolatrice calcolatrice = (ICalcolatrice) rmiRegistry.lookup("calcolatrice");
			MyListener listener = new MyListener(calcolatrice, qconn);
			receiver.setMessageListener(listener);
			
			System.out.println("[Dispatcher] avviato");
	

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
