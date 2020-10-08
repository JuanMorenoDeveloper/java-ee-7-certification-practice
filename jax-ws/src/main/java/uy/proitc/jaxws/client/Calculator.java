package uy.proitc.jaxws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.4.0 2020-09-25T11:02:49.598-03:00 Generated source
 * version: 3.4.0
 */
@WebService(targetNamespace = "http://jaxws.proitc.uy/wsdl", name = "Calculator")
@XmlSeeAlso({ObjectFactory.class})
public interface Calculator {

  @WebMethod
  @RequestWrapper(localName = "multiply", targetNamespace = "http://jaxws.proitc.uy/wsdl", className = "uy.proitc.jaxws.client.Multiply")
  @ResponseWrapper(localName = "multiplyResponse", targetNamespace = "http://jaxws.proitc.uy/wsdl", className = "uy.proitc.jaxws.client.MultiplyResponse")
  @WebResult(name = "return", targetNamespace = "")
  int multiply(

      @WebParam(name = "arg0", targetNamespace = "")
          int arg0,
      @WebParam(name = "arg1", targetNamespace = "")
          int arg1
  );

  @WebMethod
  @RequestWrapper(localName = "sum", targetNamespace = "http://jaxws.proitc.uy/wsdl", className = "uy.proitc.jaxws.client.Sum")
  @ResponseWrapper(localName = "sumResponse", targetNamespace = "http://jaxws.proitc.uy/wsdl", className = "uy.proitc.jaxws.client.SumResponse")
  @WebResult(name = "return", targetNamespace = "")
  int sum(

      @WebParam(name = "arg0", targetNamespace = "")
          int arg0,
      @WebParam(name = "arg1", targetNamespace = "")
          int arg1
  );
}