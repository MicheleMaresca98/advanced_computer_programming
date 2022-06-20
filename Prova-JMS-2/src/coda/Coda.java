package coda;

import java.util.Vector;

public interface Coda {
	
	public int size();
	public boolean empty();
	public boolean full();
	public Vector<String> takeAll();
	public void put(String value);

}
