package uy.com.proitc.jaxrs;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testng.PropertiesBuilder;
import org.apache.openejb.util.NetworkUtil;
import org.junit.Test;
import org.junit.runner.RunWith;

@EnableServices(value = "jaxrs", httpDebug = true)
@RunWith(ApplicationComposer.class)
public class ProductEndpointUnitTest {

  private final int port = NetworkUtil.getNextAvailablePort();

  @Test
  public void whenFindAllWithMediaJson_thenGetProductList()
      throws IOException, InterruptedException {
    String url = String.format("http://localhost:%d/%s", port, "demo/api/product");
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String result = response.body();

    assertThat(result).isNotNull().contains("{\"name\":\"Soap\"}");
  }


  @Test
  public void whenFindAllWithMediaXML_thenGetProductList() {
    final String message = WebClient
        .create("http://localhost:" + port)
        .path("demo/api/product")
        .accept(MediaType.APPLICATION_XML)
        .get(String.class);

    assertThat(message).isNotNull().contains("<product><name>Soap</name></product>")
        .contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
  }

  @Test
  public void whenFindById_thenGetProduct() {
    final Product result = WebClient
        .create("http://localhost:" + port)
        .path("demo/api/product/1")
        .accept(MediaType.APPLICATION_JSON)
        .get(Product.class);

    assertThat(result).isEqualTo(new Product("Flour"));
  }

  @Test
  public void whenExistsByName_thenGetResult() {
    final boolean exists = WebClient
        .create("http://localhost:" + port)
        .path("demo/api/product/exitsByName")
        .query("name", "Flour")
        .accept(MediaType.APPLICATION_JSON)
        .get(Boolean.class);

    assertThat(exists).isTrue();
  }

  @Test
  public void whenPositionOf_thenGetPosition() {
    final int position = WebClient
        .create("http://localhost:" + port)
        .path("demo/api/product/positionOf")
        .matrix("name", "Flour")
        .accept(MediaType.APPLICATION_JSON)
        .get(Integer.class);

    assertThat(position).isEqualTo(1);
  }

  @Test
  public void whenPositionOfWithNull_thenGetBadRequestException() {
    Throwable thrown = catchThrowable(() -> {
      WebClient
          .create("http://localhost:" + port)
          .path("demo/api/product/positionOf")
          .accept(MediaType.APPLICATION_JSON)
          .get(Integer.class);
    });

    assertThat(thrown).isInstanceOf(BadRequestException.class).hasMessageContaining("400");
  }

  @Test
  public void whenAsyncOperationWithLowSleepTime_thenGetOKResponse() {
    final String result = WebClient
        .create("http://localhost:" + port)
        .path("demo/api/product/asyncOperation")
        .query("sleepTime", 500)
        .accept(MediaType.APPLICATION_JSON)
        .get(String.class);

    assertThat(result).isEqualTo("OK");
  }

  @Configuration
  public Properties configuration() {
    return new PropertiesBuilder().p("httpejbd.port", String.valueOf(port)).build();
  }

  @Module
  @Classes(cdi = true, value = {ProductEndpoint.class, RestApplication.class})
  public WebApp war() {
    return new WebApp()
        .contextRoot("/demo");
  }
}
