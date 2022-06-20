package disk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import interfaces.ILogger;

public class LoggerProxy implements ILogger {
	
	private int portaLogger;
	
	public LoggerProxy(int portaLogger) {
		this.portaLogger = portaLogger;
	}

	@Override
	public void registraDato(int dato) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket socket = new DatagramSocket();
			String messageString = new String("registraDato#" + dato);
			DatagramPacket packet = new DatagramPacket(messageString.getBytes(), messageString.getBytes().length,
					InetAddress.getLocalHost(), portaLogger);
		
			socket.send(packet);
			byte[] data = new byte[65508];
			DatagramPacket responsePacket = new DatagramPacket(data, data.length);
			socket.receive(responsePacket);
			String responseString = new String(responsePacket.getData(), 0, responsePacket.getLength());
			System.out.println("[LoggerProxy]: ha registrato il dato " + dato + " sul logger alla porta " + portaLogger + 
					" con esito " + responseString);
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
