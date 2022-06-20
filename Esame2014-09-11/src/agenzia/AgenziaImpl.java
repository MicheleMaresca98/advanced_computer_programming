package agenzia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AgenziaImpl extends AgenziaSkeleton {
	
	private int B;
	private Lock lock;
	private Condition acq;
	
	public AgenziaImpl(int port, int B) {
		super(port);
		this.B = B;
		lock = new ReentrantLock();
		acq = lock.newCondition();
	}

	@Override
	public void acquista(int quantita) {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			while(this.B < quantita) {
				acq.await();
			}
			Thread.sleep((int)(4 + Math.random()*5)*1000);
			this.B = this.B - quantita;
			System.out.println("[AgenziaImpl]: acquistati " + quantita + " biglietti, totale biglietti: " + this.B);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void vendi(int quantita) {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			Thread.sleep((int)(4 + Math.random()*5)*1000);
			this.B = this.B + quantita;
			System.out.println("[AgenziaImpl]: venduti " + quantita + " biglietti, totale biglietti: " + this.B);
			acq.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

}
