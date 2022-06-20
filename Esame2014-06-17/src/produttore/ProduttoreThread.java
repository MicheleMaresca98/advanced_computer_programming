package produttore;

import java.rmi.RemoteException;

import interfaces.IMagazzino;

public class ProduttoreThread extends Thread{
	
	private IMagazzino magazzino;
	
	public ProduttoreThread(IMagazzino magazzino) {
		this.magazzino = magazzino;
	}
	
	public void run() {
		try {
			Thread.sleep((int)(1 + Math.random()*3)*1000);
			int id = (int) (1 + Math.random()*100);
			magazzino.deposita(id);
			System.out.println("[ProduttoreThread]: ha depositato l'articolo con id " + id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
