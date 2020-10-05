package uy.proitc;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

@RunWith(Arquillian.class)
public class DemoTest {

  @ArquillianResource
  private URL baseUrl;

  private WebClient webClient;

  @RunAsClient
  @Test
  public void testHelloWorld() throws Exception {
    HtmlPage page = webClient.getPage(baseUrl);
    assertThat(page.asXml()).contains("Hello World!");
  }

  @Before
  public void before() {
    webClient = new WebClient();
  }

  @After
  public void after() {
    webClient.close();
  }

  @Deployment
  public static WebArchive createDeployment() {
    return create(WebArchive.class).
        addAsWebResource(new File("src/main/webapp/index.jsp")).
        addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"));
  }
}
