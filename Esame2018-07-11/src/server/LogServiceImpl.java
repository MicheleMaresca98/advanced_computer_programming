package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

import interfaces.ILogService;

public class LogServiceImpl extends UnicastRemoteObject implements ILogService{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8346740480286948321L;
	
	private Semaphore requests;
	
	protected LogServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		int N = 3; //numero richeiste concorrenti massime
		requests = new Semaphore(N);
	}
	 

	@Override
	public boolean stampa(int temperatura) throws RemoteException {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(requests.tryAcquire()) {
			return flag;
		}
		synchronized(this) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./temperature.txt", true)));
				pw.println("Temperatura: " + temperatura);
				pw.flush();
				System.out.println("[LogServiceImpl] Temperatura stampata: " + temperatura);
				flag = true;
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		requests.release();
		return flag;
	}

}
