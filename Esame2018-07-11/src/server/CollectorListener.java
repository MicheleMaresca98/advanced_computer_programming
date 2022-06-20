package server;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import interfaces.ILogService;

public class CollectorListener implements MessageListener {
	
	private ILogService logService;
	
	public CollectorListener(ILogService logService) {
		this.logService = logService;
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
		try {
			MapMessage message = (MapMessage) arg0;
			int temperatura = message.getInt("temperatura");
			CollectorThread thread = new CollectorThread(logService, temperatura);
			thread.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
