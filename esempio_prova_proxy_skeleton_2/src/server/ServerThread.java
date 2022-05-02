package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import magazzino.IMagazzino;

public class ServerThread extends Thread{
	
	private Socket socket;
	IMagazzino serverImpl;
	PrintStream fileOut1;
	PrintStream fileOut2;
	
	public ServerThread(Socket socket, IMagazzino serverImpl, PrintStream fileOut1, PrintStream fileOut2) {
		this.socket = socket;
		this.serverImpl = serverImpl;
		this.fileOut1 = fileOut1;
		this.fileOut2 = fileOut2;
		
	}
	
	public void run() {
		try {
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			
			String method = inStream.readUTF();
			
			if(method.equals("deposita")) {
				String articolo = inStream.readUTF();
				int id = inStream.readInt();
				
				serverImpl.deposita(articolo, id);
				outStream.writeUTF("ack");
				
			}else if(method.equals("preleva")) {
				String articolo = inStream.readUTF();
				if(articolo.equals("laptop")) {
					int id = serverImpl.preleva(articolo);
					fileOut1.println("ID articolo: "+ id);
					outStream.writeInt(id);

				}else if(articolo.equals("smartphone")) {
					int id = serverImpl.preleva(articolo);
					fileOut2.println("ID articolo: "+ id);
					outStream.writeInt(id);
					
				}else {
					System.out.println("[ServerThread]: preleva, articolo errato");
				}

				
				
			}else {
				System.out.println("[ServerThread]: method invalid");
			}
			
			outStream.close();
			inStream.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
