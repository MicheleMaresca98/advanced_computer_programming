package printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IPrinter;

public class PrinterThread extends Thread{
	
	private Socket socket;
	private IPrinter printer;
	
	public PrinterThread(Socket socket, IPrinter printer) {
		this.socket = socket;
		this.printer = printer;
	}
	
	public void run() {
		
		try {
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			String method = inStream.readUTF();
			if(method.equals("print")) {
				String docName = inStream.readUTF();
				boolean flag = printer.print(docName);
				outStream.writeBoolean(flag);
				
			}else {
				System.out.println("[PrinterThread]: invalid method");
			}
			
			inStream.close();
			outStream.close();
			
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
