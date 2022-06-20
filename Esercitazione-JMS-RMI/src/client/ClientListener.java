package client;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ClientListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		MapMessage message = (MapMessage) arg0;
		try {
			
			int id_articolo = message.getInt("id_articolo");
			System.out.println("[ClientListener]: ha ricevuto " + id_articolo);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
