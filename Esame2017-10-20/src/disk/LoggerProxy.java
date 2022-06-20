package disk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import interfaces.ILogger;

public class LoggerProxy implements ILogger{

	private int port;
	
	public LoggerProxy(int port) {
		this.port = port;
	}
	@Override
	public void stampa(int value) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket socket = new DatagramSocket();
			String message = new String("stampa#"+value);
			DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, 
					InetAddress.getLocalHost(), port);
			socket.send(packet);
			byte[] data = new byte[65508];
			DatagramPacket packetResponse = new DatagramPacket(data, data.length);
			socket.receive(packetResponse);
			String esito = new String(packetResponse.getData(), 0, packetResponse.getLength());
			System.out.println("[LoggerProxy] ha stampato il valore " + value + " con esito " + esito);
			socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
