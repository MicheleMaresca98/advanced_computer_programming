package server;

import java.io.*;
import java.net.*;

public class UDPServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket socket = new DatagramSocket(8050);
			byte[] data = new byte[65508];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			System.out.println("[Server]: attesa pacchetto UDP...");
			socket.receive(packet);
			System.out.println("[Server]: pacchetto ricevuto.");
			
			byte[] receivedData = packet.getData();
			String s = new String(receivedData, 0, packet.getLength());
			System.out.println("[Server]: contenuto pacchetto: " + s);
			s = "Ok client, pacchetto ricevuto";
			DatagramPacket response = new DatagramPacket(s.getBytes(),s.getBytes().length,packet.getAddress(),packet.getPort());
			Thread.sleep(5000);
			System.out.println("[Server]: invio pacchetto risposta...");
			socket.send(response);
			System.out.println("[Server]: pacchetto inviato");
			socket.close();
			
		}catch(SocketException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

}
