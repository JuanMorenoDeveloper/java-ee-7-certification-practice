package uy.proitc.jaxws.server;

import javax.jws.WebService;

@WebService(targetNamespace = "http://jaxws.proitc.uy/wsdl")
public interface Calculator {
  int sum(int num1, int num2);

  int multiply(int num1, int num2);
}
