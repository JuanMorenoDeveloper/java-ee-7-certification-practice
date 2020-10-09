package uy.com.proitc.jaxrs;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@RequestScoped
@Path("/product")
public class ProductEndpoint {

  private final List<Product> products = List
      .of(new Product("Soap"), new Product("Flour"), new Product("Juice"));

  private ManagedExecutorService executorService;

  @Resource
  public void setExecutorService(ManagedExecutorService executorService) {
    this.executorService = executorService;
  }

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

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/exitsByName")
  public boolean exitsByName(@QueryParam("name") String name) {
    return products.stream().anyMatch(product -> product.getName().equals(name));
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/positionOf")
  public int positionOf(@NotNull @MatrixParam("name") String name) {
    return products.stream().map(Product::getName).collect(toList()).indexOf(name);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/asyncOperation")
  public void asyncOperation(@Suspended AsyncResponse response,
      @Positive @QueryParam("sleepTime") int sleepTime) {
    response.setTimeout(200, TimeUnit.MILLISECONDS);
    response.setTimeoutHandler(this::handleTimeout);
    executorService.submit(() -> {
      try {
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      response.resume("OK");
    });
  }

  private void handleTimeout(AsyncResponse asyncResponse) {
    asyncResponse
        .resume(Response.status(Status.SERVICE_UNAVAILABLE).entity("Operation time out").build());
  }
}
