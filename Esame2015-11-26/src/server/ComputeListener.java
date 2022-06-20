package server;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;

public class ComputeListener implements MessageListener {
	
	private QueueConnection qconn;
	private Compute compute;
	
	public ComputeListener(QueueConnection qconn, Compute compute) {
		this.qconn = qconn;
		this.compute = compute;
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		ObjectMessage message = (ObjectMessage) arg0;
		ComputeThread thread = new ComputeThread(qconn, message, compute);
		thread.start();
	}

}
