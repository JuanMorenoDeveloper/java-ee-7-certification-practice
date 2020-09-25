package uy.proitc.jaxws.server;

import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;

@RequestScoped
@WebService(
    portName = "CalculatorPort",
    serviceName = "CalculatorService",
    targetNamespace = "http://jaxws.proitc.uy/wsdl",
    endpointInterface = "uy.proitc.jaxws.server.Calculator")
public class DefaultCalculator implements Calculator {

  @Override
  public int sum(int num1, int num2) {
    return num1 + num2;
  }

  @Override
  public int multiply(int num1, int num2) {
    return num1 * num2;
  }
}
