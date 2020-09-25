package uy.proitc.jaxws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the uy.proitc.jaxws.wsdl package. &lt;p&gt;An ObjectFactory allows you to
 * programatically construct new instances of the Java representation for XML content. The Java
 * representation of XML content can consist of schema derived interfaces and classes representing
 * the binding of schema type definitions, element declarations and model groups.  Factory methods
 * for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _Multiply_QNAME = new QName("http://jaxws.proitc.uy/wsdl", "multiply");
  private final static QName _MultiplyResponse_QNAME = new QName("http://jaxws.proitc.uy/wsdl",
      "multiplyResponse");
  private final static QName _Sum_QNAME = new QName("http://jaxws.proitc.uy/wsdl", "sum");
  private final static QName _SumResponse_QNAME = new QName("http://jaxws.proitc.uy/wsdl",
      "sumResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: uy.proitc.jaxws.wsdl
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link Multiply }
   */
  public Multiply createMultiply() {
    return new Multiply();
  }

  /**
   * Create an instance of {@link MultiplyResponse }
   */
  public MultiplyResponse createMultiplyResponse() {
    return new MultiplyResponse();
  }

  /**
   * Create an instance of {@link Sum }
   */
  public Sum createSum() {
    return new Sum();
  }

  /**
   * Create an instance of {@link SumResponse }
   */
  public SumResponse createSumResponse() {
    return new SumResponse();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Multiply }{@code >}
   *
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link Multiply }{@code >}
   */
  @XmlElementDecl(namespace = "http://jaxws.proitc.uy/wsdl", name = "multiply")
  public JAXBElement<Multiply> createMultiply(Multiply value) {
    return new JAXBElement<Multiply>(_Multiply_QNAME, Multiply.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link MultiplyResponse }{@code >}
   *
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link MultiplyResponse }{@code >}
   */
  @XmlElementDecl(namespace = "http://jaxws.proitc.uy/wsdl", name = "multiplyResponse")
  public JAXBElement<MultiplyResponse> createMultiplyResponse(MultiplyResponse value) {
    return new JAXBElement<MultiplyResponse>(_MultiplyResponse_QNAME, MultiplyResponse.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Sum }{@code >}
   *
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link Sum }{@code >}
   */
  @XmlElementDecl(namespace = "http://jaxws.proitc.uy/wsdl", name = "sum")
  public JAXBElement<Sum> createSum(Sum value) {
    return new JAXBElement<Sum>(_Sum_QNAME, Sum.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link SumResponse }{@code >}
   *
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link SumResponse }{@code >}
   */
  @XmlElementDecl(namespace = "http://jaxws.proitc.uy/wsdl", name = "sumResponse")
  public JAXBElement<SumResponse> createSumResponse(SumResponse value) {
    return new JAXBElement<SumResponse>(_SumResponse_QNAME, SumResponse.class, null, value);
  }

}
