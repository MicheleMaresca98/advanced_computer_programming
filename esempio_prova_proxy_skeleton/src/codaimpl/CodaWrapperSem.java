package codaimpl;

import java.util.concurrent.Semaphore;

import coda.*;

public class CodaWrapperSem extends CodaWrapper {

	
	// Inserire semafori
	Semaphore producer;
	Semaphore consumer;
	
	
	public CodaWrapperSem ( Coda c ){
		super (c);
		
		// Inizializzare semafori
		producer = new Semaphore(coda.getSize());
		consumer = new Semaphore(0);
	}
	
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con semafori
		try {
			producer.acquire();
			synchronized(coda) {
				coda.inserisci(i);
			}
			consumer.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con semafori
		try {
			consumer.acquire();
			synchronized(coda) {
				x = coda.preleva();
			}
			producer.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
					
		
		
		return x;
	}
	
	
}