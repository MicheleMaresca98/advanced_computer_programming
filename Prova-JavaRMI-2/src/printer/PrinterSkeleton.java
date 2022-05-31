package printer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.IPrinter;

public class PrinterSkeleton implements IPrinter{
	
	private IPrinter printerImpl; 
	private int port;
	
	public PrinterSkeleton(IPrinter printerImpl, int port) {
		this.printerImpl = printerImpl; 
		this.port = port;
	}
	
	public void runSkeleton() {
		try {
			
			ServerSocket server = new ServerSocket(port);
		
			System.out.println("[PrinterSkeleton] in attesa sulla porta " + port);
			while(true) {
				Socket socket = server.accept();
				Thread printerThread = new PrinterThread(socket, this);
				printerThread.start();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean print(String docName) {
		// TODO Auto-generated method stub
		return printerImpl.print(docName);
	}
	
	

}
