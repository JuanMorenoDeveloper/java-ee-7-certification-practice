package uy.com.proitc.servlet;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static uy.com.proitc.servlet.ProductDisplayFilter.FILTER_NAME;
import static uy.com.proitc.servlet.ProductDisplayServlet.PRODUCT_SERVLET_NAME;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testing.RandomPort;
import org.junit.Test;
import org.junit.runner.RunWith;

@EnableServices("httpejbd")
@RunWith(ApplicationComposer.class)
public class ProductDisplayServiceUnitTest {

  @RandomPort("httpejbd")
  private URL httpEjbPort;

  @Test
  public void whenSendGetProductServlet_thenGetProductList()
      throws IOException, InterruptedException {
    String url = String.format("%s%s", httpEjbPort.toString(), "demo/products");
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String result = response.body();

    assertThat(result).isNotNull().contains("Soap").contains("Filter content");
  }

  @Module
  @Classes(cdi = true, value = {ProductService.class})
  public WebApp war() {
    return new WebApp()
        .contextRoot("/demo")
        .addServlet(PRODUCT_SERVLET_NAME, ProductDisplayServlet.class.getName(), "/products")
        .addFilter(FILTER_NAME, ProductDisplayFilter.class.getName(), "/*");
  }
}
