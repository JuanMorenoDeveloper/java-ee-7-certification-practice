package uy.proitc.jaxws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for sumResponse complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this
 * class.
 *
 * &lt;pre&gt; &amp;lt;complexType name="sumResponse"&amp;gt; &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt; &amp;lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 * &amp;lt;/sequence&amp;gt; &amp;lt;/restriction&amp;gt; &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt; &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sumResponse", propOrder = {
    "_return"
})
public class SumResponse {

  @XmlElement(name = "return")
  protected int _return;

  /**
   * Gets the value of the return property.
   */
  public int getReturn() {
    return _return;
  }

  /**
   * Sets the value of the return property.
   */
  public void setReturn(int value) {
    this._return = value;
  }

}
