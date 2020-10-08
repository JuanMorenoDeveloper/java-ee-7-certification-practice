package uy.com.proitc.jaxrs;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/products")
public class ProductEndpoint {

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Product> findAll() {
    return List.of(new Product("Soap"), new Product("Flour"), new Product("Juice"));
  }
}
