package sportello;

import interfaces.IBroker;

public class SportelloServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int portSportello = 9002;
		int portBroker = 9000;
		SportelloImpl sportello = new SportelloImpl(portSportello);
		IBroker broker = new BrokerProxy(portBroker);
		broker.sottoscrivi(portSportello);
		System.out.println("[SportelloServer]: avviato e sottoscritto");

		sportello.runSkeleton();

	}

}
