package broker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IBroker;

public class BrokerThread extends Thread {
	
	private Socket socket;
	private IBroker broker;
	
	public BrokerThread(Socket socket, IBroker broker) {
		this.socket = socket;
		this.broker = broker;
	}
	
	public void run() {
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			String method = in.readUTF();
			if(method.equals("sottoscrivi")) {
				int portSportello = in.readInt();
				broker.sottoscrivi(portSportello);
				out.writeUTF("ack");
				System.out.println("[BrokerThread]: ha sottoscritto sportello con port " + portSportello);
			}else if(method.equals("inoltraRichiesta")) {
				int id_cliente = in.readInt();
				boolean esito = broker.inoltraRichiesta(id_cliente);
				out.writeBoolean(esito);
				System.out.println("[BrokerThread]: ha inoltrato richiesta di id_cliente " + id_cliente + 
						" con esito " + esito);

			}else {
				System.out.println("[BrokerThread]: invalid method");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
