package uy.proitc.scheduled;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "target")
})
public class MDBListener implements MessageListener {

  public static final String TEXT = "foo";
  public static boolean ok = false;

  @Override
  public void onMessage(final Message message) {
    try {
      ok = message instanceof TextMessage && TEXT
          .equals(((TextMessage) message).getText());
    } catch (final JMSException e) {
      // no-op
    }
  }

  public static boolean sync() {
    return ok;
  }
}
