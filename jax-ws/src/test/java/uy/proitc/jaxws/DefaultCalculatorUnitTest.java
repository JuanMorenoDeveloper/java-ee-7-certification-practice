package uy.proitc.jaxws;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testng.PropertiesBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import uy.proitc.jaxws.client.CalculatorService;
import uy.proitc.jaxws.server.Calculator;
import uy.proitc.jaxws.server.DefaultCalculator;

@EnableServices("jax-ws")
@RunWith(ApplicationComposer.class)
public class DefaultCalculatorUnitTest {

  private static final int JAX_WS_PORT = 9090;//NetworkUtil.getNextAvailablePort();

  private static final String wsdl = "http://127.0.0.1:" + JAX_WS_PORT + "/demo/calculator?wsdl";

  @WebServiceRef(wsdlLocation = wsdl)
  private Calculator client;

  @Test
  public void whenSum_thenGetResult() throws MalformedURLException {
    final Service service = Service.create(
        new URL(wsdl),
        new QName("http://jaxws.proitc.uy/wsdl", "CalculatorService"));
    final Calculator calc = service.getPort(Calculator.class);

    int result = calc.sum(2, 2);

    assertThat(result).isEqualTo(4);
  }

  @Test
  public void givenClient_whenSum_thenGetResult() throws MalformedURLException {
    uy.proitc.jaxws.client.Calculator client = new CalculatorService(new URL(wsdl))
        .getCalculatorPort();

    int result = client.sum(2, 2);

    assertThat(result).isEqualTo(4);
  }

  @Test
  public void givenRef_whenSum_thenGetResult() {
    int result = client.sum(2, 2);

    assertThat(result).isEqualTo(4);
  }

  @Test
  public void whenQueryForWsdl_thenGetExpectedName() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(wsdl))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    assertThat(response.body()).contains("name=\"CalculatorService\"");
  }

  @Configuration
  public Properties configuration() {
    return new PropertiesBuilder().p("httpejbd.port", Integer.toString(JAX_WS_PORT)).build();
  }

  @Module
  @Classes(cdi = true, value = {DefaultCalculator.class})
  public WebApp war() {
    return new WebApp()
        .contextRoot("/demo")
        .addServlet("calculatorEndpoint", DefaultCalculator.class.getName(), "/calculator");
  }

}
