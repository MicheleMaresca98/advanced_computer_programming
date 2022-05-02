package server;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CounterImpl count = new CounterImpl();
		
		//count.runSkeleton();
		
		//per delega
		CounterSkeletonDelega counterSkeletonDelega = new CounterSkeletonDelega(count);
		counterSkeletonDelega.runSkeleton();

	}

}
