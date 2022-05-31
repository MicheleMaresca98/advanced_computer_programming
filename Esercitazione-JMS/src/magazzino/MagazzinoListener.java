package magazzino;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;

import coda.Coda;

public class MagazzinoListener implements MessageListener{
	
	private QueueConnection qconn;
	private Coda coda;
	
	public MagazzinoListener(Coda coda, QueueConnection qconn) {
		this.coda = coda;
		this.qconn = qconn;
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		MapMessage message = (MapMessage) arg0;
		MagazzinoThread thread = new MagazzinoThread(coda, qconn, message);
		thread.start();
	}

}
