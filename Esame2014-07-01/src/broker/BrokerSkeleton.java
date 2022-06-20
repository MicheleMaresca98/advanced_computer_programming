package broker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.IBroker;

public class BrokerSkeleton implements IBroker {
	
	private int port;
	private IBroker broker;
	
	public BrokerSkeleton(int port, IBroker broker) {
		this.port = port;
		this.broker = broker;
	}
	
	public void runSkeleton() {
		try {
			ServerSocket server = new ServerSocket(port);
		
			System.out.println("[BrokerSkeleton]: avviato alla porta " + port);
			while(true) {
				Socket socket = server.accept();
				Thread thread = new BrokerThread(socket, this);
				thread.start();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sottoscrivi(int portSportello) {
		// TODO Auto-generated method stub
		broker.sottoscrivi(portSportello);
	}

	@Override
	public boolean inoltraRichiesta(int id_cliente) {
		// TODO Auto-generated method stub
		return broker.inoltraRichiesta(id_cliente);
	}

}
