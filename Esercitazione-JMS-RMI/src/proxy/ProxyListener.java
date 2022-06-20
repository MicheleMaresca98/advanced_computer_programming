package proxy;

import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import interfaces.IServer;

public class ProxyListener implements MessageListener {
	
	private IServer server;
	
	public ProxyListener(IServer server) {
		this.server = server;
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		MapMessage message = (MapMessage) arg0;
		try {
			String tiporichiesta = message.getString("tiporichiesta");
			if(tiporichiesta.equals("deposita")) {
				int id_articolo = message.getInt("id_articolo");
				System.out.println("[ProxyListener]: ha prelevato id_articolo " + id_articolo);
				server.deposita(id_articolo);
			}else if(tiporichiesta.equals("preleva")) {
				int id_articolo = server.preleva();
				Thread sender = new Sender(id_articolo);
				sender.start();
			}else {
				System.out.println("[ProxyListener]: tiporichiesta invalida");
			}
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
