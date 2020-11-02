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
import uy.com.proitc.model.Product;
import uy.com.proitc.model.SearchProduct;
import uy.com.proitc.page.object.SearchPageObject;

@RunWith(Arquillian.class)
public class SearchProductUnitTest {

  @ArquillianResource
  private URL baseUrl;
  @Drone
  private WebDriver browser;
  @Page
  private SearchPageObject search;

  @Deployment
  public static WebArchive createDeployment() {
    return create(WebArchive.class).
        addAsWebResource(new File("src/main/webapp/search.xhtml")).
        addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml")).
        addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml")).addClasses(Product.class,
        SearchProduct.class);
  }

  @RunAsClient
  @Test
  public void whenShowDetailsProduct3_thenShowResult() {
    browser.get(baseUrl.toExternalForm() + "/search.xhtml");

    assertThat(search.showDetailsProduct3().getResult().getText()).contains("Details 3");
  }
}
