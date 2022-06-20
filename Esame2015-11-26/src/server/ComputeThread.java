package server;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import computedata.ComputeData;

public class ComputeThread extends Thread {
	
	private QueueConnection qconn;
	private ObjectMessage message;
	private Compute compute;
	
	public ComputeThread(QueueConnection qconn, ObjectMessage message, Compute compute) {
		this.qconn = qconn;
		this.message = message;
		this.compute = compute;
	}
	
	public void run() {
		try {
			ComputeData computeData = (ComputeData) message.getObject();
			String operazione = computeData.getOperazione();
			int operando1 = computeData.getOperando1();
			int operando2 = computeData.getOperando2();
			int risultato = compute.getResult(operazione, operando1, operando2);
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue replyQueue = (Queue) message.getJMSReplyTo();
			QueueSender sender = qsession.createSender(replyQueue);
			MapMessage resultMessage = qsession.createMapMessage();
			resultMessage.setInt("risultato", risultato);
			sender.send(resultMessage);
			System.out.println("[ComputerThread]: ha ricevuto " + computeData + " ed ha ricevuto risultato " + risultato);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
