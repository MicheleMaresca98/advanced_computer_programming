package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.IPrinter;

public class PrinterProxy implements IPrinter{
	
	private String ip;
	private int port;
	
	public PrinterProxy(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	@Override
	public boolean print(String docName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			
			Socket socket = new Socket(InetAddress.getByName(ip), port);
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			
			outStream.writeUTF("print");
			outStream.writeUTF(docName);
			flag = inStream.readBoolean();
			
			System.out.println("[PrinterProxy] print("+docName+") ha ricevuto "+flag);
			inStream.close();
			outStream.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
