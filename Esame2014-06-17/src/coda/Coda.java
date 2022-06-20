package coda;

public interface Coda {
	
	public boolean empty();
	public boolean full();
	public int size();
	public void deposita(int value);
	public int preleva();

}
