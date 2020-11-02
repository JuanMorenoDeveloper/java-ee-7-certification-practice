package uy.com.proitc.model;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class SearchProduct implements Serializable {

  private final List<Product> products = List
      .of(new Product(1, "Soap", "Details 1"), new Product(2, "Flour", "Details 2"),
          new Product(3, "Juice", "Details 3"));

  private String currentDetails = "";

  public void showDetails(int id) {
    currentDetails = products.stream().filter(product -> product.getId() == id).findFirst()
        .orElseGet(() -> new Product(0, "", "Not Found")).getDetails();
  }

  public void updateMessages(ActionEvent actionEvent) {
    FacesContext.getCurrentInstance()
        .addMessage("Message", new FacesMessage("Searching...", "New Result"));
  }

  public String getTitle() {
    return "FormModel";
  }

  public String getCurrentDetails() {
    return currentDetails;
  }

  public List<Product> getProducts() {
    return products;
  }
}
