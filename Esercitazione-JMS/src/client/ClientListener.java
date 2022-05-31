package client;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ClientListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub

		try {
			MapMessage message = (MapMessage)arg0;
			System.out.println("[ClientListener] ha ricevuto: " + message.getString("operazione") + " " + message.getInt("valore"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
