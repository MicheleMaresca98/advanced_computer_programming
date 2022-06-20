package sportello;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

import interfaces.IGestore;
import interfaces.ISportello;

public class SportelloImpl extends UnicastRemoteObject implements ISportello{

	/**
	 * 
	 */
	private static final long serialVersionUID = -940058180418871662L;
	private String pathfile;
	private Semaphore maxReqs;

	protected SportelloImpl(IGestore gestore, String pathfile) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.pathfile = pathfile;
		this.maxReqs = new Semaphore(2);		
	}

	@Override
	public boolean serviRichiesta(int idCliente) throws RemoteException {
		// TODO Auto-generated method stub
		boolean esito = false;
		try {
			if(!maxReqs.tryAcquire()){
				System.out.println("[SportelloImpl]: la richiesta con idCliente " + idCliente + " non è stata servita, "
						+ "esito = " + esito);
				return esito;
			}
			synchronized(this) {
				Thread.sleep((int)(1 + Math.random()*5)*1000);
			
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(pathfile, true)));
				pw.println("idCliente: " + idCliente);
				pw.flush();
				esito = true;
				pw.close();
				System.out.println("[SportelloImpl]: ha servito la richiesta di idCliente " + idCliente);
			}
			maxReqs.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

}
