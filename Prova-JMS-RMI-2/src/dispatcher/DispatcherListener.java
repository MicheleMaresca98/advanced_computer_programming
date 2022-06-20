package dispatcher;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class DispatcherListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		MapMessage message = (MapMessage) arg0;
		Thread thread = new DispatcherThread(message);
		thread.start();
	}

}
