package gestore;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import interfaces.IDeposito;

public class DepositoProxy implements IDeposito {
	
	private int port;
	
	public DepositoProxy(int port) {
		this.port = port;
	}

	@Override
	public boolean deposita(int id_articolo) {
		// TODO Auto-generated method stub
		boolean esito = false;
		
		try {
			DatagramSocket socket = new DatagramSocket();
		
			String message = new String("deposita#" + id_articolo);
			DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, 
					InetAddress.getLocalHost(), port);
			socket.send(packet);
			byte[] data = new byte[65508];
			DatagramPacket responsePacket = new DatagramPacket(data, data.length);
			socket.receive(responsePacket);
			String rcvdString = new String(responsePacket.getData(), 0, responsePacket.getLength());
			esito = Boolean.parseBoolean(rcvdString);
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
		return esito;
	}

}
