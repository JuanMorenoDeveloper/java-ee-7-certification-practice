package uy.com.proitc.page.object;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("/index.xhtml")
public class IndexPageObject {

  @FindBy(xpath = "/html/body")
  private WebElement body;

  public WebElement getBody() {
    return this.body;
  }
}
