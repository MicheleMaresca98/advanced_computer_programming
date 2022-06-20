package coda;

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
	public void deposita(int value) {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			while(coda.full()) {
				producer.wait();
			}
			coda.deposita(value);
			consumer.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
				consumer.wait();
			}
			value = coda.preleva();
			producer.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return value;
	}

}
