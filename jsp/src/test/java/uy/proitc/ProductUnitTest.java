package uy.proitc;

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
import uy.proitc.page.object.ProductPageObject;

@RunWith(Arquillian.class)
public class ProductUnitTest {

  @ArquillianResource
  private URL baseUrl;
  @Drone
  private WebDriver browser;
  @Page
  private ProductPageObject product;

  @RunAsClient
  @Test
  public void whenGetProductPageWithDrone_thenGetContents() {
    browser.get(baseUrl.toExternalForm() + "/product.jsp");

    String content = product.getSection().getText();

    assertThat(content).isNotNull().contains("Soap");
  }

  @Deployment
  public static WebArchive createDeployment() {
    return create(WebArchive.class).
        addAsWebResource(new File("src/main/webapp/product.jsp")).
        addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"));
  }
}
