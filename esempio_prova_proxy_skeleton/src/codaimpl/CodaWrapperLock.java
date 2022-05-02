package codaimpl;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import coda.*;

public class CodaWrapperLock extends CodaWrapper {
	
	// Inserire Lock e variabili condition
	Lock lock;
	Condition empty;
	Condition full;

	
	public CodaWrapperLock( Coda c ){
		super (c);
		
		// Inizializzare lock e variabili condition
		lock = new ReentrantLock();
		empty = lock.newCondition();
		full = lock.newCondition();
		
	}
	
	
	public void inserisci( int i){
				
		
		// Implementare sincronizzazione con lock e variabili condition
		try {
			lock.lock();
			
			try {
				while(coda.full()) {
					empty.await();
				}
				coda.inserisci(i);
				full.signal();
			}finally {
				lock.unlock();
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con lock e variabili condition
		try {
		lock.lock();
		
		try {
			while(coda.empty()) {
				full.await();
			}
			x= coda.preleva();
			empty.signal();
		}finally {
			lock.unlock();
		}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
				
			
		
		return x;
	}

	
}
