package uy.proitc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
public class HelloWorldTest {

  @ArquillianResource
  private URL baseUrl;
  @Drone
  private WebDriver browser;
  @Page
  private IndexPageObject index;

  @RunAsClient
  @Test
  public void whenGetIndexPage_thenGetHelloWorld() throws Exception {
    try (var webClient = new WebClient()) {
      HtmlPage page = webClient.getPage(baseUrl);
      assertThat(page.asXml()).contains("Hello World!");
    }
  }

  @RunAsClient
  @Test
  public void whenGetIndexPageWithDrone_thenGetHelloWorld() throws Exception {
    browser.get(baseUrl.toExternalForm() + "/index.jsp");
    assertThat(index.getH1().getText()).contains("Hello World!");
  }

  @Deployment
  public static WebArchive createDeployment() {
    return create(WebArchive.class).
        addAsWebResource(new File("src/main/webapp/index.jsp")).
        addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"));
  }
}
