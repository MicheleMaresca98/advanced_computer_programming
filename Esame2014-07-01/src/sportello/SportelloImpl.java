package sportello;

import java.util.concurrent.Semaphore;

public class SportelloImpl extends SportelloSkeleton {
	
	Semaphore maxReqs;

	public SportelloImpl(int port) {
		super(port);
		// TODO Auto-generated constructor stub
		maxReqs = new Semaphore(2);
	}

	@Override
	public boolean serviRichiesta(int id_cliente) {
		// TODO Auto-generated method stub
		boolean esito = false;
		try {
			
			if(!maxReqs.tryAcquire()) {
				System.out.println("[SportelloImpl]: non ha soddisfatto la richiesta di id_cliente " + id_cliente);
				return false;
			}
			synchronized(this) {
				Thread.sleep((int)(5 + Math.random()*6)*1000);
				System.out.println("[SportelloImpl]: ha servito la richiesta di id_cliente " + id_cliente);
				esito = true;
			}
			maxReqs.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

}
