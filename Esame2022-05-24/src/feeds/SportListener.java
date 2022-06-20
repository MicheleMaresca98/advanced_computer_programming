package feeds;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SportListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
		try {
			TextMessage message = (TextMessage) arg0;
			System.out.println("[SportListener]: ha ricevuto la notizia " + message.getText());
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sport.txt", true)));
			pw.println("Notizia: " + message.getText());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
