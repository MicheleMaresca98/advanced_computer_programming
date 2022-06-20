package coda;

public abstract class CodaWrapper implements Coda{
	
	protected Coda coda;
	
	public CodaWrapper(Coda coda) {
		this.coda = coda;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return coda.size();
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return coda.empty();
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return coda.full();
	}

}
