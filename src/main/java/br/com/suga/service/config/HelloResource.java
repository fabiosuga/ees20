package br.com.suga.service.config;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("hello")
public class HelloResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloResource
     */
    public HelloResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.suga.teste.resources.TesteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        String teste = "{\n"
                + "\"greeting\":\"Hello from ees20.\"\n"
                + "}";

        return teste;
    }
}
