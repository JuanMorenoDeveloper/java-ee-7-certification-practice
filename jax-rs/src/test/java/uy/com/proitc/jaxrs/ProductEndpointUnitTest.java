package uy.com.proitc.jaxrs;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
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
