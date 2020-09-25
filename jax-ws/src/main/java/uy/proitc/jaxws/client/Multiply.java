package uy.proitc.jaxws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for multiply complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this
 * class.
 *
 * &lt;pre&gt; &amp;lt;complexType name="multiply"&amp;gt; &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt; &amp;lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 * &amp;lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 * &amp;lt;/sequence&amp;gt; &amp;lt;/restriction&amp;gt; &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt; &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiply", propOrder = {
    "arg0",
    "arg1"
})
public class Multiply {

  protected int arg0;
  protected int arg1;

  /**
   * Gets the value of the arg0 property.
   */
  public int getArg0() {
    return arg0;
  }

  /**
   * Sets the value of the arg0 property.
   */
  public void setArg0(int value) {
    this.arg0 = value;
  }

  /**
   * Gets the value of the arg1 property.
   */
  public int getArg1() {
    return arg1;
  }

  /**
   * Sets the value of the arg1 property.
   */
  public void setArg1(int value) {
    this.arg1 = value;
  }

}
