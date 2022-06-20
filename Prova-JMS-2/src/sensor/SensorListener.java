package sensor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import coda.Coda;

public class SensorListener implements MessageListener {
	
	private Coda coda;
	
	public SensorListener(Coda coda) {
		this.coda = coda;
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
		try {
			TextMessage message = (TextMessage) arg0;
			String comando = message.getText();
			TManager tManager = new TManager(comando, coda);
			tManager.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
