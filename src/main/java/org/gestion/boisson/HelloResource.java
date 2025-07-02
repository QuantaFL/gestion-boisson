package org.gestion.boisson;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.gestion.boisson.utils.JPAUtil;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
//    @Produces("application/json")
    public String hello() {

        JPAUtil.createEntityManager();
        return "Hello, World!";
    }
}