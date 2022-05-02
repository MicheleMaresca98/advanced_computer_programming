package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			String s = new String("Hello World!");
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(s.getBytes(), s.getBytes().length, InetAddress.getLocalHost(), 8050);
			
			System.out.println("[Client]: invio pacchetto UDP...");
			socket.send(packet);
			System.out.println("[Client]: pacchetto inviato.");
			
			byte[] data = new byte[65508];
			DatagramPacket response = new DatagramPacket(data, data.length);
			
			System.out.println("[Client]: attesa pacchetto UDP...");
			socket.receive(response);
			System.out.println("[Client]: pacchetto ricevuto.");
			
			
			s = new String(response.getData(), 0, response.getLength());
			System.out.println("[Client]: contenuto pacchetto: " + s);
			
			socket.close();
		}catch(SocketException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
