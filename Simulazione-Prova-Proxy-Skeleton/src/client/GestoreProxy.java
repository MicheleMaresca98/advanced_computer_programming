package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.IGestore;

public class GestoreProxy implements IGestore{
	
	private int port;
	
	public GestoreProxy(int port) {
		this.port = port;
	}

	@Override
	public boolean prenota(int matricola, boolean limitazione) {
		// TODO Auto-generated method stub
		boolean esito = false;
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
		
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("prenota");
			out.writeInt(matricola);
			out.writeBoolean(limitazione);
			esito = in.readBoolean();
			System.out.println("[GestoreProxy]: ha richiesto prenotazione con matricola " + matricola +
					" e limitazione " + limitazione + " ed ha ricevuto esito " + esito);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
		
	}

}
