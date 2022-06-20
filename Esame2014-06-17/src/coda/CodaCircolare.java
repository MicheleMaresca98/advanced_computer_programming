package coda;

public class CodaCircolare implements Coda {
	
	private int size;
	private int[] data;
	private int head;
	private int tail;
	private int elem;
	
	public CodaCircolare(int size) {
		this.size = size;
		this.data = new int[this.size];
		this.head = 0;
		this.tail = 0;
		this.elem = 0;
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
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void deposita(int value) {
		// TODO Auto-generated method stub
		data[tail%size] = value;
		tail++;
		elem++;
		
	}

	@Override
	public int preleva() {
		// TODO Auto-generated method stub
		int value = data[head%size];
		head++;
		elem--;
		return value;
	}

}
