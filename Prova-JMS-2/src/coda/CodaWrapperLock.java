package coda;

import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper {
	
	private Lock lock;
	private Condition producer;
	private Condition consumer;

	public CodaWrapperLock(Coda coda) {
		super(coda);
		// TODO Auto-generated constructor stub
		lock = new ReentrantLock();
		producer = lock.newCondition();
		consumer = lock.newCondition();
	}

	@Override
	public Vector<String> takeAll() {
		// TODO Auto-generated method stub
		Vector<String> values = new Vector<String>();
		lock.lock();
		try {
			while(coda.empty()) {
				consumer.await();
			}
			values = coda.takeAll();
			producer.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return values;
	}

	@Override
	public void put(String value) {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			while(coda.full()) {
				producer.await();
			}
			coda.put(value);
			consumer.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

}
