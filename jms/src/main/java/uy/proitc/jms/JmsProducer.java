package uy.proitc.jms;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@RequestScoped
public class JmsProducer {

  private Queue destination;
  private JMSContext context;

  public void sendMessage(String message) {
    context.createProducer().send(destination, context.createTextMessage(message));
  }

  @Resource(name = "target")
  public void setDestination(Queue destination) {
    this.destination = destination;
  }

  @Inject
  public void setContext(JMSContext context) {
    this.context = context;
  }
}
