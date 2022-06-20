package deposito;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.IDeposito;

public class DepositoThread extends Thread {
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	private IDeposito deposito;
	
	public DepositoThread(DatagramSocket socket, DatagramPacket packet, IDeposito deposito) {
		this.socket = socket;
		this.packet = packet;
		this.deposito = deposito;
	}
	
	public void run() {
		String rcvdString = new String(packet.getData(), 0, packet.getLength());
		StringTokenizer tknzr = new StringTokenizer(rcvdString, "#");
		String method = tknzr.nextToken();
		if(method.equals("deposita")) {
			int id_articolo = Integer.valueOf(tknzr.nextToken()).intValue();
			boolean esito = deposito.deposita(id_articolo);
			System.out.println("[DepositoThread]: deposita id_articolo " + id_articolo);
			String responseString = Boolean.toString(esito);
			DatagramPacket responsePacket = new DatagramPacket(responseString.getBytes(), responseString.getBytes().length,
					packet.getAddress(), packet.getPort());
			try {
				socket.send(responsePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("[DepositoThread]: invalid method");
		}
	}
	

}
