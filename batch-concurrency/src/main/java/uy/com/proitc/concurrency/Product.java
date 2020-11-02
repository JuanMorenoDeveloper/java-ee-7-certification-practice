package uy.com.proitc.concurrency;

import java.util.Objects;

public class Product {

  private int id;

  private String name;

  private double revenue;

  public Product() {
    this(0, "", 0.0d);
  }

  public Product(int id, String name, double revenue) {
    this.id = id;
    this.name = name;
    this.revenue = revenue;
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

  public double getRevenue() {
    return revenue;
  }

  public void setRevenue(double revenue) {
    this.revenue = revenue;
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
           Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, revenue);
  }

  @Override
  public String toString() {
    return "Product{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", revenue='" + revenue + '\'' +
           '}';
  }
}
