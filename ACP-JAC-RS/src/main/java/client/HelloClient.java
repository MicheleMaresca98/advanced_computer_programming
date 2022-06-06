package client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class HelloClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8090/helloworld/");
		
		String reply = target.path("personalHello").queryParam("name", "Raffaele").queryParam("insegnamento", "ACP")
				.request(MediaType.TEXT_PLAIN).get(String.class);
		
		System.out.println("[RISPOSTA] " + reply);

	}

}
