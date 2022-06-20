package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8554870247919593025L;

	protected PrinterImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void printDoc(String nomeDocumento) throws RemoteException {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(5000);
			String pathfile = "docs.txt";
			System.out.println("[PrinterImpl]: stampa nomeDocumento " + nomeDocumento);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(pathfile, true)));
			pw.println("nomeDocumento: " + nomeDocumento);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
