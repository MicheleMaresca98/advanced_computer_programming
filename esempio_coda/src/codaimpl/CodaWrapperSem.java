package codaimpl;

import java.util.concurrent.*;

import coda.*;

public class CodaWrapperSem extends CodaWrapper {

	
	// Inserire semafori
	private Semaphore postiDisp;
	private Semaphore elemDisp;
	
	
	public CodaWrapperSem ( Coda c ){
		super (c);
		
		// Inizializzare semafori
		postiDisp = new Semaphore(coda.getSize());
		elemDisp = new Semaphore(0);
	}
	
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con semafori
		try {
			postiDisp.acquire();
			try {
				synchronized(coda) {
					coda.inserisci(i);
			}
		}finally {
			elemDisp.release();
		}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con semafori
		try {
			elemDisp.acquire();
			try {
				synchronized(coda) {
					x = coda.preleva();
			}
			}finally {
				postiDisp.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		return x;
	}
	
	
}