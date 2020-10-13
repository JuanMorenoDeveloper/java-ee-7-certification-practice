package uy.com.proitc.websocket;

import java.io.IOException;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/socket")
public class ProductEndpoint {

  @OnOpen
  public void onOpen(final Session session) throws IOException {
    session.getBasicRemote().sendText("Hello");
  }
}
