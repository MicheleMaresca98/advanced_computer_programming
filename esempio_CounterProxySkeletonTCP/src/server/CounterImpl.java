package server;



public class CounterImpl extends CounterSkeleton { // per ereditarietà
	
// per delega
//import service.ICounter;
//public class CounterImpl implements ICounter { 
	
	private int count;
	
	public CounterImpl() {
		count=0;
	}

	@Override
	public void inc() {
		// TODO Auto-generated method stub
		count = count + 1;
		
		
	}

	@Override
	public void sum(int value) {
		// TODO Auto-generated method stub
		count = count + value;
		
	}

	@Override
	public int get() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public void square() {
		// TODO Auto-generated method stub
		count = count*count;
		
	}

}
