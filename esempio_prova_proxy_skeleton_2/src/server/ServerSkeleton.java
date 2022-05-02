package server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import magazzino.IMagazzino;

public abstract class ServerSkeleton implements IMagazzino{
	
	public void runSkeleton() {
		try {
			int port = 9000;
			ServerSocket server = new ServerSocket(port);
			PrintStream fileOut1 = new PrintStream(new FileOutputStream("./file1.txt"));
			PrintStream fileOut2 = new PrintStream(new FileOutputStream("./file2.txt"));

			
			System.out.println("[ServerSkeleton]: server in attesa sulla porta "+port);
			while(true) {
				Socket socket = server.accept();
				ServerThread worker = new ServerThread(socket, this, fileOut1, fileOut2);
				worker.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
