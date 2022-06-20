package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
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

import org.apache.activemq.spring.ActiveMQConnectionFactory;

import computedata.ComputeData;

public class ClientThread extends Thread {
	
	public void run() {
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
			QueueSender sender = qsession.createSender(req);
			ComputeData computeData = new ComputeData();
			int operando1 = (int)(Math.random()*100);
			int operando2 = (int)(Math.random()*100);
			computeData.setOperando1(operando1);
			computeData.setOperando2(operando2);
			String operazione = null;
			if(Math.random() < 0.5) {
				operazione = "SUM";
				Queue rplsum = (Queue) ctx.lookup("RPLSUM");
				computeData.setOperazione(operazione);
				ObjectMessage message = qsession.createObjectMessage();
				message.setObject(computeData);
				message.setJMSReplyTo(rplsum);
				sender.send(message);
				QueueReceiver receiver = qsession.createReceiver(rplsum);
				MapMessage response = qsession.createMapMessage();
				response = (MapMessage) receiver.receive();
				System.out.println("[ClientThread]: ha inviato messaggio " + computeData + " ed ha ricevuto " 
						+ response.getInt("risultato"));
	
			}else {
				operazione = "MUL";
				Queue rplmul = (Queue) ctx.lookup("RPLMUL");
				computeData.setOperazione(operazione);
				ObjectMessage message = qsession.createObjectMessage();
				message.setObject(computeData);
				message.setJMSReplyTo(rplmul);
				sender.send(message);
				QueueReceiver receiver = qsession.createReceiver(rplmul);
				MapMessage response = qsession.createMapMessage();
				response = (MapMessage) receiver.receive();
				System.out.println("[ClientThread]: ha inviato messaggio " + computeData + " ed ha ricevuto " 
						+ response.getInt("risultato"));
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
