package client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Actuator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DispatcherProxy dispatcherProxy = new DispatcherProxy();
			PrintStream fileOut = new PrintStream(new FileOutputStream("./cmdlog.txt"));
			
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int response = dispatcherProxy.getCmd();
				
				fileOut.println ( "Comando = "+ response);
				System.out.println("[Actuator]: ha ricevuto il comando " + response);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
