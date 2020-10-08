package uy.proitc.page.object;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("/product.jsp")
public class ProductPageObject {

  @FindBy(xpath = "/html/body/section")
  private WebElement section;

  public WebElement getSection() {
    return section;
  }
}
