package client;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread sender = new Sender();
		sender.start();
		
		Thread receiver = new Receiver();
		receiver.start();
		
	}

}
