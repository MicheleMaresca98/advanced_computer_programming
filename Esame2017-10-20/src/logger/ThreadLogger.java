package logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.ILogger;

public class ThreadLogger extends Thread{
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	private ILogger logger;
	
	public ThreadLogger(DatagramSocket socket, DatagramPacket packet, ILogger logger) {
		this.socket = socket;
		this.packet = packet;
		this.logger = logger;
	}
	
	public void run() {
		String stringRcvd = new String(packet.getData(), 0, packet.getLength());
		StringTokenizer strTknzr = new StringTokenizer(stringRcvd, "#");
		String method = strTknzr.nextToken();
		if(method.equals("stampa")) {
			int value = Integer.valueOf(strTknzr.nextToken()).intValue();
			logger.stampa(value);
			System.out.println("[ThreadLogger] ha stampato " + value);
			String stringResponse = "ack";
			DatagramPacket responsePacket = new DatagramPacket(stringResponse.getBytes(), stringResponse.getBytes().length,
					packet.getAddress(), packet.getPort());
			try {
				socket.send(responsePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("[ThreadLogger] invalid method");
		}
	}
	

}
