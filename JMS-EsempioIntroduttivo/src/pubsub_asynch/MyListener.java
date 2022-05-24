package pubsub_asynch;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		System.out.println("Messaggio ricevuto: " + arg0);
	}

}
