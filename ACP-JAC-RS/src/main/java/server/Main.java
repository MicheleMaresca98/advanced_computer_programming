package server;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.core.UriBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    
    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	// create a resource config that scans for JAX-RS resources and providers
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("server");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        URI baseURI = UriBuilder.fromPath("http://localhost:8090").build();
        GrizzlyHttpServerFactory.createHttpServer(baseURI, resourceConfig);
    }
}

