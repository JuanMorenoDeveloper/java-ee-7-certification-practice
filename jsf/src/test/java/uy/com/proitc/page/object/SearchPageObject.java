package uy.com.proitc.page.object;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("/search.xhtml")
public class SearchPageObject {

  @FindBy(id = "search:result")
  private WebElement result;

  @FindBy(id = "search:table:2:show")
  private WebElement showBtn3;

  public WebElement getResult() {
    return result;
  }

  public SearchPageObject showDetailsProduct3() {
    showBtn3.click();
    return this;
  }
}
