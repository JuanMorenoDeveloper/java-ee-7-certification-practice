package uy.com.proitc.jsp;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("productController")
@RequestScoped
public class ProductController {

  public List<Product> findAll() {
    return List.of(new Product("Soap"), new Product("Flour"), new Product("Juice"));
  }
}
