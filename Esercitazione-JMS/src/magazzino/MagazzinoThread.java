package magazzino;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import coda.Coda;

public class MagazzinoThread extends Thread{
	
	private QueueConnection qconn;
	private Coda coda;
	private MapMessage message;
	
	public MagazzinoThread(Coda coda, QueueConnection qconn, MapMessage message) {
		this.coda = coda;
		this.qconn = qconn;
		this.message = message;
	}
	
	public void run() {
		try {
			String operazione = message.getString("operazione");
			if(operazione.equals("deposita")) {
				int valore = message.getInt("valore");
				coda.inserisci(valore);
				System.out.println("[MagazzinoThread] ha depositato " + valore);
			}else {
				int valore = coda.preleva();
				QueueSession qsession = qconn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				
				MapMessage response = qsession.createMapMessage();
				response.setString("operazione", "id_value");
				response.setInt("valore", valore);
				QueueSender sender = qsession.createSender((Queue) message.getJMSReplyTo());
				sender.send(response);
				System.out.println("[MagazzinoThread] ha prelevato " + valore);
				
				sender.close();
				qsession.close();
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
