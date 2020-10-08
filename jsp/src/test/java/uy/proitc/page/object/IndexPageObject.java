package uy.proitc.page.object;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("/index.jsp")
public class IndexPageObject {

  @FindBy(xpath = "/html/body/h1")
  private WebElement h1;

  public WebElement getH1() {
    return h1;
  }
}
