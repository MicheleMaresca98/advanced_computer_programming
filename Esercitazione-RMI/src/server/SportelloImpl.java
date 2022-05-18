package server;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

import interfaces.ISportello;


public class SportelloImpl extends UnicastRemoteObject implements ISportello{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Semaphore maxRequests;
	private Semaphore maxConcurrentRequests;
	
	private PrintWriter pw;

	protected SportelloImpl() throws RemoteException{
		// TODO Auto-generated constructor stub
		maxRequests = new Semaphore(5);
		maxConcurrentRequests = new Semaphore(3);
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter("richieste.txt", true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean serviRichiesta(int idCliente) throws RemoteException{
		// TODO Auto-generated method stub
		boolean flag = false;
		
		if(maxRequests.tryAcquire() == false) {
			System.out.println("[SportelloImpl]: Raggiunto limite massimo di richieste");
			System.out.println("[SportelloImpl]: Richiesta da cliente " + idCliente + " terminata con esito "+flag);
			return flag;
		}
			
		try {
			maxConcurrentRequests.acquire();
			Thread.sleep((int)(1 + Math.random()*5)*1000);
			pw.println("idCliente: " + idCliente);
			pw.flush();
			flag = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}finally {
			maxConcurrentRequests.release();
			maxRequests.release();
		}
		
		return flag;
	}

}
