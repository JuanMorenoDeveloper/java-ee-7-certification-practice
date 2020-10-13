package uy.com.proitc.websocket;

import static org.awaitility.Awaitility.await;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import org.assertj.core.api.AssertionsForClassTypes;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunAsClient
@RunWith(Arquillian.class)
public class ProductEndpointUnitTest {

  @ArquillianResource
  private URL url;

  @Test
  public void whenConnect_thenGetHelloMsg()
      throws URISyntaxException, IOException, DeploymentException {
    var client = new ProductClient();
    Session session = ContainerProvider.getWebSocketContainer()
        .connectToServer(client,
            new URI("ws", url.toURI().getUserInfo(), url.getHost(), url.getPort(), url.getPath() +
                "/socket",
                null, null));

    await()
        .untilAsserted(() -> AssertionsForClassTypes.assertThat(client.getMessage())
            .isEqualTo("Hello"));

    session.close();
  }

  @Deployment(testable = false)
  public static WebArchive app() {
    return ShrinkWrap.create(WebArchive.class)
        .addClasses(ProductEndpoint.class);
  }
}
