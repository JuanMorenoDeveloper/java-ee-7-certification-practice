package uy.com.proitc.jaxrs;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

import java.net.URL;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ProductEndpointAsyncUnitTest {

  @ArquillianResource
  private URL baseUrl;

  @Test
  public void whenAsyncOperationWithLongSleepTime_thenGetTimeOutResponse() {
    Throwable thrown = catchThrowable(() -> {
      WebClient
          .create(baseUrl.toURI())
          .path("api/product/asyncOperation")
          .query("sleepTime", 1500)
          .accept(MediaType.APPLICATION_JSON)
          .get(String.class);
    });

    assertThat(thrown).isInstanceOf(ServiceUnavailableException.class)
        .hasMessageContaining("503");
  }

  @Deployment(testable = false)
  public static WebArchive createDeployment() {
    return create(WebArchive.class)
        .addClasses(ProductEndpoint.class, Product.class, RestApplication.class);
  }

}
