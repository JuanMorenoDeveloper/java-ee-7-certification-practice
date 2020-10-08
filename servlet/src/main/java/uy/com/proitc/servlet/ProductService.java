package uy.com.proitc.servlet;

import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ProductService {

  public List<Product> findAll() {
    return List.of(new Product("Soap"), new Product("Flour"), new Product("Juice"));
  }
}
