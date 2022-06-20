package broker;

import interfaces.IBroker;

public class BrokerServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 9000;
		IBroker brokerImpl = new BrokerImpl();
		BrokerSkeleton brokerSkeleton = new BrokerSkeleton(port, brokerImpl);
		System.out.println("[BrokerServer]: avviato alla porta " + port);
		brokerSkeleton.runSkeleton();

	}

}
