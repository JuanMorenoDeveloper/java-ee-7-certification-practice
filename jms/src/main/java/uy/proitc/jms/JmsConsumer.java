package uy.proitc.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class JmsConsumer implements MessageListener {

  private static Logger LOG = Logger.getLogger(JmsConsumer.class.getName());
  private static boolean received = false;

  public static boolean isReceived() {
    return received;
  }

  @Override
  public void onMessage(final Message message) {
    try {
      String text = message.getBody(String.class);
      received = !text.isEmpty();
      LOG.info(String.format("Receiving %s", text));
    } catch (final JMSException e) {
      LOG.log(Level.SEVERE, String.format("Error processing message %s", message), e);
    }
  }
}
