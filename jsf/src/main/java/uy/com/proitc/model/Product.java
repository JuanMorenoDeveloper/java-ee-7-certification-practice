package uy.com.proitc.model;

import java.util.Objects;

public class Product {

  private int id;

  private String name;

  private String details;

  public Product() {
    this(0, "", "");
  }

  public Product(int id, String name, String details) {
    this.id = id;
    this.name = name;
    this.details = details;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id == product.id &&
           Objects.equals(name, product.name) &&
           Objects.equals(details, product.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, details);
  }

  @Override
  public String toString() {
    return "Product{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", details='" + details + '\'' +
           '}';
  }
}
