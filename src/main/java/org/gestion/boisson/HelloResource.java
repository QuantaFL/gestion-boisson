package org.gestion.boisson;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.gestion.boisson.utils.JPAUtil;

@Path("/hello-world")
public class HelloResource {
    @Inject
    @GET
    @Produces("text/plain")
//    @Produces("application/json")
    public String hello() {
        var em = JPAUtil.createEntityManager();
        return "Hello, World!";
    }
}