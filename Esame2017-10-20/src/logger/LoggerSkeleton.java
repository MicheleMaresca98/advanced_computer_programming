package logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.ILogger;

public class LoggerSkeleton implements ILogger{
	
	private ILogger logger;
	
	public LoggerSkeleton(ILogger logger) {
		this.logger = logger;
	}
	
	public void runSkeleton() {
		try {
			int port = 9000;

			DatagramSocket socket = new DatagramSocket(port);
			System.out.println("[LoggerSkeleton]: avviato");
			while(true) {
				byte[] data = new byte[65508];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				Thread thread = new ThreadLogger(socket, packet, this);
				thread.start();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stampa(int value) {
		// TODO Auto-generated method stub
		logger.stampa(value);
	}

}
