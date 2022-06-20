package client;

import java.rmi.RemoteException;

import interfaces.IGestore;

public class ClientThread extends Thread{
	
	private int R;
	private IGestore gestore;
	
	public ClientThread(int R, IGestore gestore) {
		this.R = R;
		this.gestore = gestore;
	}
	
	public void run() {
		try {
			for(int i=0; i<R; i++) {
				Thread.sleep((int)(1 + Math.random()*3)*1000);
				int id_articolo = (int)(1 + Math.random()*100);
				boolean esito = gestore.richiestaDeposito(id_articolo);
				System.out.println("[ClientThread]: ha richiesto deposito id_articolo " + 
						id_articolo + " e ha ricevuto esito " + esito);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
