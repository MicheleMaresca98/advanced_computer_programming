package coda;

import java.util.Vector;

public class CodaCircolare implements Coda {
	
	private int size;
	private String[] data;
	private int head;
	private int tail;
	private int elem;
	
	public CodaCircolare(int size) {
		this.size = size;
		this.data = new String[this.size];
		this.head = 0;
		this.tail = 0;
		this.elem = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return (elem == 0);
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return (elem == size);
	}

	@Override
	public Vector<String> takeAll() {
		// TODO Auto-generated method stub
		Vector<String> values = new Vector<String>();
		while(!empty()) {
			values.add(data[head%size]);
			head++;
			elem--;
		}
		return values;
	}

	@Override
	public void put(String value) {
		// TODO Auto-generated method stub
		data[tail%size] = value;
		tail++;
		elem++;
	}

}
