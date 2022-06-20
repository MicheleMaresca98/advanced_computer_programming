package disk;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import interfaces.ILogger;

public class DiskThread extends Thread {
	
	private MapMessage message;
	
	public DiskThread(MapMessage message) {
		this.message = message;
	}
	
	public void run() {
		try {
			int dato = message.getInt("dato");
			int portaLogger = message.getInt("portaLogger");
			System.out.println("[DiskThread]: ha ricevuto dato " + dato);
			ILogger logger = new LoggerProxy(portaLogger);
			logger.registraDato(dato);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
