package client;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		try {
			MapMessage message = (MapMessage) arg0;
			int risultato = message.getInt("risultato");
			System.out.println("[Client-MyListener] ha ricevuto il risultato " + risultato);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
