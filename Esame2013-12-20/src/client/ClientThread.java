package client;

import java.rmi.RemoteException;

import interfaces.IGestore;

public class ClientThread extends Thread{
	
	private IGestore gestore;
	private int R;
	
	public ClientThread(IGestore gestore, int R) {
		this.gestore = gestore;
		this.R = R;
	}
	
	public void run() {
		for(int i=0; i<R; i++) {
			
			try {
				int idCliente = (int)(1 + Math.random()*100);
				gestore.sottoponiRichiesta(idCliente);
				System.out.println("[ClientThread]: ha sottoposto la richiesta con idCliente " + idCliente);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
