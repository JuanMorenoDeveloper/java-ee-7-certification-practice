package uy.com.proitc.jaxrs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {

  @XmlElement
  private final String name;

  public Product() {
    this("");
  }

  public Product(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Product{" +
        "name='" + name + '\'' +
        '}';
  }
}
