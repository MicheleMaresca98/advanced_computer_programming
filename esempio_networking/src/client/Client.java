package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 8050);
			System.out.println("[Client]: Socket creata.");
			DataOutputStream toServer = new DataOutputStream(s.getOutputStream());
			DataInputStream fromServer = new DataInputStream(s.getInputStream());
			
			toServer.writeUTF("Hello World!");
			
			System.out.println("[Client]: attesa risposta...");
			String resp = fromServer.readUTF();
			System.out.println("[Client]: risposta server: " + resp);
			
			toServer.close();
			fromServer.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
