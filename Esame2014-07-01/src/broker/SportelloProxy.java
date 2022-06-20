package broker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.ISportello;

public class SportelloProxy implements ISportello {
	
	private int port;
	
	public SportelloProxy(int port) {
		this.port = port;
	}

	@Override
	public boolean serviRichiesta(int id_cliente) {
		// TODO Auto-generated method stub
		boolean esito = false;
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("serviRichiesta");
			out.writeInt(id_cliente);
			esito = in.readBoolean();
			
			System.out.println("[SportelloProxy]: ha inviato serviRicheista con id_cliente " + id_cliente +
					" ed ha ricevuto esito " + esito);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

}
