package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import magazzino.IMagazzino;

public class ClientProxy implements IMagazzino{

	@Override
	public void deposita(String articolo, int id) {
		// TODO Auto-generated method stub
		Socket socket;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 9000);
		
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			
			outStream.writeUTF("deposita");
			outStream.writeUTF(articolo);
			outStream.writeInt(id);
			
			String response = inStream.readUTF();
			System.out.println("[ClientProxy]: deposita ha ricevuto " + response);
			
			outStream.close();
			inStream.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int preleva(String articolo) {
		Socket socket;
		int id = 0;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 9000);
		
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			
			outStream.writeUTF("preleva");
			outStream.writeUTF(articolo);
			id = inStream.readInt();
			
			System.out.println("[ClientProxy]: preleva ha ricevuto " + id);
			
			outStream.close();
			inStream.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

}
