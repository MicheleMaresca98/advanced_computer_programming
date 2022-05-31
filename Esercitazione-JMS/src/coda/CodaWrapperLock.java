package coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper{

	private Lock lock;
	private Condition produttore;
	private Condition consumatore;
	
	public CodaWrapperLock(Coda coda) {
		super(coda);
		// TODO Auto-generated constructor stub
		lock = new ReentrantLock();
		produttore = lock.newCondition();
		consumatore = lock.newCondition();
	}

	@Override
	public void inserisci(int value) {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			while(coda.full()) {
				produttore.await();
			}
			
			coda.inserisci(value);
			consumatore.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}

	@Override
	public int preleva() {
		// TODO Auto-generated method stub
		int value = -1;
		lock.lock();
		try {
			while(coda.empty()) {
				consumatore.await();
			}
			
			value = coda.preleva();
			produttore.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return value;
	}

}
