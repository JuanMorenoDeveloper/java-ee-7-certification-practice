package uy.com.proitc.jaxrs;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/product")
public class ProductEndpoint {

  private final List<Product> products = List
      .of(new Product("Soap"), new Product("Flour"), new Product("Juice"));

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Product> findAll() {
    return products;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Product findBy(@PathParam("id") int id) {
    return products.get(id);
  }
}
