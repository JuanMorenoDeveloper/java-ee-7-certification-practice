package uy.com.proitc.websocket;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ProductClient {

  private static final Logger log = Logger.getLogger(ProductClient.class.getName());

  private final AtomicReference<String> message = new AtomicReference<>();

  @OnOpen
  public void onOpen(final Session session,
      final EndpointConfig endpointConfig) {
    log.info("onOpen");
  }

  @OnMessage
  public void onMessage(final String message) {
    this.message.set(message);
  }

  @OnError
  public void onError(final Session session,
      final Throwable throwable) {
    log.log(Level.SEVERE, throwable, () -> "onError");
  }

  @OnClose
  public void onClose(final Session session,
      final CloseReason closeReason) {
    log.info(() -> String.format("onClose: %s", closeReason));
  }

  public String getMessage() {
    return message.get();
  }
}
