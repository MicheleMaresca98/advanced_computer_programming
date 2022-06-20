package loggerserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.ILogger;

public class LoggerSkeleton implements ILogger {
	
	private int port;
	private ILogger logger;
	
	public LoggerSkeleton(int port, ILogger logger) {
		this.port = port;
		this.logger = logger;
	}
	
	public void runSkeleton() {
		try {
			DatagramSocket socket = new DatagramSocket(port);
		
			System.out.println("[LoggerSkeleton]: avviato in ascolto su porta " + port);
			while(true) {
				byte[] data = new byte[65508];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				LoggerThread thread = new LoggerThread(socket, packet, this);
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
	public void registraDato(int dato) {
		// TODO Auto-generated method stub
		logger.registraDato(dato);
	}

}
