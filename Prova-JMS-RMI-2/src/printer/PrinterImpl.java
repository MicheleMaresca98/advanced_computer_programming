package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6621690522097626824L;
	private Lock lock;
	
	protected PrinterImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		lock = new ReentrantLock();

	}

	@Override
	public void printDoc(String docName) throws RemoteException {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			Thread.sleep(5000);
			System.out.println("[PrinterImpl]: stampa docName: " + docName);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./listadocumenti.txt", true)));
			pw.println("docName: " + docName);
			pw.flush();
			pw.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	

}
