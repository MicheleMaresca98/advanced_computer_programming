package broker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.IAgenzia;

public class AgenziaProxy implements IAgenzia{
	
	private int port;
	
	public AgenziaProxy(int port) {
		this.port = port;
	}

	@Override
	public void acquista(int quantita) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("acquista");
			out.writeInt(quantita);
			String response = in.readUTF();
			System.out.println("[AgenziaProxy]: acquista " + quantita + " biglietti e riceve " + response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void vendi(int quantita) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("vendi");
			out.writeInt(quantita);
			String response = in.readUTF();
			System.out.println("[AgenziaProxy]: vende " + quantita + " biglietti e riceve " + response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
