package codaimpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import coda.*;

public class CodaWrapperLock extends CodaWrapper {
	
	// Inserire Lock e variabili condition
	private Lock lock;
	private Condition empty;
	private Condition full;
	

	
	public CodaWrapperLock( Coda c ){
		super (c);
		
		// Inizializzare lock e variabili condition
		lock = new ReentrantLock();
		empty =  lock.newCondition();
		full = lock.newCondition();
		
		
	}
	
	
	public void inserisci( int i){
				
		
		// Implementare sincronizzazione con lock e variabili condition
		lock.lock();
		try {
			while(coda.full()) {
				try {
					empty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			coda.inserisci(i);
			full.signal();
		}
		finally {
			lock.unlock();
		}		
		
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con lock e variabili condition
		lock.lock();
		try {
			while(coda.empty()) {
				try {
					full.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			x= coda.preleva();
			empty.signal();
			}
		finally {
			lock.unlock();
		}
		
		
		return x;
	}

	
}
