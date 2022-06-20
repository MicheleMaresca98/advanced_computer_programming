package server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;
import interfaces.Observer;

public class ObserverServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			PrintWriter pw = new PrintWriter(new FileWriter(args[1], true));
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IDispatcher dispatcher = (IDispatcher)rmiRegistry.lookup("dispatcher");
			
			Observer observer = new ObserverImpl(dispatcher, pw);
			String tipo = args[0];
			
			dispatcher.attach(observer, tipo);
			System.out.println("[ObserverServer] observer di tipo: "+ tipo + " sottoscritto al dispatcher");
		
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
