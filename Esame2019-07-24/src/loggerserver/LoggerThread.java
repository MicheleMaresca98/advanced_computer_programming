package loggerserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.ILogger;

public class LoggerThread extends Thread {
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	private ILogger logger;
	
	public LoggerThread(DatagramSocket socket, DatagramPacket packet, ILogger logger) {
		this.socket = socket;
		this.packet = packet;
		this.logger = logger;
	}
	
	public void run() {
		
		String stringRecvd = new String(packet.getData(), 0, packet.getLength());
		StringTokenizer tknzr = new StringTokenizer(stringRecvd, "#");
		String method = tknzr.nextToken();
		if(method.equals("registraDato")) {
			int dato = Integer.valueOf(tknzr.nextToken()).intValue();
			logger.registraDato(dato);
			System.out.println("[LoggerThread]: ha registrato il dato " + dato);
			String response = "ack";
			DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length, 
					packet.getAddress(), packet.getPort());
			try {
				socket.send(responsePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("[LoggerThread]: invalid method");
		}
		
	}

}
