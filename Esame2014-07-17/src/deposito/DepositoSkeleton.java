package deposito;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.IDeposito;

public class DepositoSkeleton implements IDeposito {
	
	private int port;
	private IDeposito deposito;
	
	public DepositoSkeleton(int port, IDeposito deposito) {
		this.port = port;
		this.deposito = deposito;
	}
	
	public void runSkeleton() {
		
		try {
			DatagramSocket socket = new DatagramSocket(port);
		
			System.out.println("[DepositoSkeleton]: avviato in ascolto su porta " + port);
			while(true) {
				byte[] data = new byte[65508];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				Thread thread = new DepositoThread(socket, packet, this);
				thread.start();
			}
		
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean deposita(int id_articolo) {
		// TODO Auto-generated method stub
		return deposito.deposita(id_articolo);
	}

}
