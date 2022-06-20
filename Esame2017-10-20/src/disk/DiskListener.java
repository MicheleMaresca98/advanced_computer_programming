package disk;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import interfaces.ILogger;

public class DiskListener implements MessageListener {
	
	private ILogger logger;
	
	public DiskListener(ILogger logger) {
		this.logger = logger;
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		try {
			MapMessage message = (MapMessage) arg0;
			int valore = message.getInt("valore");
			logger.stampa(valore);
			System.out.println("[DiskListener] ha stampato il valore " + valore);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
