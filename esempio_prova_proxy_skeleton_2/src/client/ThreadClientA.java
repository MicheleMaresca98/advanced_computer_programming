package client;

public class ThreadClientA extends Thread{
	
	private ClientProxy clientProxy;
	
	public ThreadClientA(ClientProxy clientProxy) {
		this.clientProxy = clientProxy;
	}
	
	public void run() {
		
		try {
			int nrichieste = 3;
			String articolo = null;
			int id = 0;
			for(int i=0;i<nrichieste;i++) {
				Thread.sleep((long) (2000 + (int)(Math.random()*2000)));
				
				if ((1+(int)(Math.random()*2)) == 1)
					articolo = "laptop";
				else
					articolo = "smartphone";
				
				id = 1 + (int)(Math.random()*100);
				
				System.out.println("[ThreadClientA]: deposita Articolo = "+articolo+", id = "+id);
				clientProxy.deposita(articolo, id);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
