package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DispatcherImpl dispatcher = new DispatcherImpl();
		dispatcher.runSkeleton();

	}

}
