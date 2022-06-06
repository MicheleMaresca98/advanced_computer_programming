package server;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "/helloworld" path)
 */
@Path("/helloworld")
public class HelloWorld {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello world, RESTful service!";
    }
    
    @Path("insegnamento/")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloACP() {
        return "Hello world, this is ACP!";
    }
    
    @Path("insegnamento/{insegnamento}/")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String insegnamentoHello(@PathParam("insegnamento") String insegnamento) {
        return "Hello world, this is " + insegnamento + "!";
    }
    
    @Path("personalHello/")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String personalHello(@QueryParam("name") String name, @QueryParam("insegnamento") String insegnamento) {
        return "Hello " + name + ", this is " + insegnamento + "!";
    }
    
    @Path("personalHelloPOST/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String postPersonalHello(@FormParam("name") String name, @FormParam("insegnamento") String insegnamento) {
        return "[POST] Hello " + name + ", this is " + insegnamento + "!";
    }
    
    
}
