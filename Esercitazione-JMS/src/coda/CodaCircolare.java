package coda;

public class CodaCircolare implements Coda{
	
	private int[] data;
	private int elem;
	private int size;
	private int head;
	private int tail;
	
	public CodaCircolare(int size) {
		this.size = size;
		this.data = new int[this.size];
		this.elem = 0;
		this.head = 0;
		this.tail = 0;
	}

	@Override
	public void inserisci(int value) {
		// TODO Auto-generated method stub
		data[tail%size] = value;
		tail++;
		elem++;
	}

	@Override
	public int preleva() {
		// TODO Auto-generated method stub
		int value;
		value = data[head%size];
		head++;
		elem--;
		return value;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return (elem==0);
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return (elem==size);
	}

}
