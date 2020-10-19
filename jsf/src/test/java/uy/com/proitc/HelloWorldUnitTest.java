package uy.com.proitc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

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
import uy.com.proitc.page.object.IndexPageObject;

@RunWith(Arquillian.class)
public class HelloWorldUnitTest {

  @ArquillianResource
  private URL baseUrl;
  @Drone
  private WebDriver browser;
  @Page
  private IndexPageObject index;

  @RunAsClient
  @Test
  public void whenGetIndexPageWithDrone_thenGetHelloWorld() {
    browser.get(baseUrl.toExternalForm() + "/index.xhtml");
    assertThat(index.getBody().getText()).contains("Hello, World");
  }

  @Deployment
  public static WebArchive createDeployment() {
    return create(WebArchive.class).
        addAsWebResource(new File("src/main/webapp/index.xhtml")).
        addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml")).
        addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"));
  }
}
