package sportello;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.IBroker;

public class BrokerProxy implements IBroker {
	
	private int port;
	
	public BrokerProxy(int port) {
		this.port = port;
	}

	@Override
	public void sottoscrivi(int portSportello) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("sottoscrivi");
			out.writeInt(portSportello);
			String esito = in.readUTF();
			System.out.println("[BrokerProxy]: sottoscritto sportello con port " + portSportello + " con esito " + esito);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean inoltraRichiesta(int id_cliente) {
		// TODO Auto-generated method stub
		
		boolean esito = false;
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
		
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("inoltraRichiesta");
			out.writeInt(id_cliente);
			esito = in.readBoolean();
			System.out.println("[BrokerProxy]: ha inoltrato la richiesta con id_cliente " + 
					id_cliente + " ed ha ricevuto esito " + esito);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}
	
	

}
