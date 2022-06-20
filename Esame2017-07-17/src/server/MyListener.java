package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import interfaces.ICalcolatrice;

public class MyListener implements MessageListener {
	
	private ICalcolatrice calcolatrice;
	private QueueConnection qconn;
	
	public MyListener(ICalcolatrice calcolatrice, QueueConnection qconn) {
		this.calcolatrice = calcolatrice;
		this.qconn = qconn;
		
	}
	
	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		try {
			MapMessage message = (MapMessage) arg0;
			String operazione = message.getString("operazione");
			int i1 = message.getInt("i1");
			int i2 = message.getInt("i2");
			System.out.println("[Dispsatcher-MyListener] ha ricevuto il messaggio con operazione " + operazione +
					" i1 = " + i1 + " i2 = " + i2);
			QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue risposta = (Queue) message.getJMSReplyTo();
			QueueSender sender = qsession.createSender(risposta);
			MapMessage messageResponse = qsession.createMapMessage();
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./operazioni.txt", true)));

			if(operazione.equals("add")) {
				int risultato = calcolatrice.add(i1, i2);
				messageResponse.setInt("risultato", risultato);
				sender.send(messageResponse);
				pw.println(operazione + " " + i1 + ", " + i2 + " = " + risultato);
				pw.flush();
			}else if (operazione.equals("mul")) {
				int risultato = calcolatrice.mul(i1, i2);
				messageResponse.setInt("risultato", risultato);
				sender.send(messageResponse);
				pw.println(operazione + " " + i1 + ", " + i2 + " = " + risultato);
				pw.flush();
			}else {
				System.out.println("[Dispatcher-MyListener] operazione invalida");
			}
			pw.close();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
