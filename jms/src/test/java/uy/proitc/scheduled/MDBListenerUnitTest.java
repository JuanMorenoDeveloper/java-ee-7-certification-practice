package uy.proitc.scheduled;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.awaitility.Awaitility.await;

import java.util.Properties;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.MessageDrivenBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testng.PropertiesBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ApplicationComposer.class)
@Classes(cdi = true)
public class MDBListenerUnitTest {

  @Resource(name = "target")
  private Queue destination;

  @Resource(name = "cf")
  private ConnectionFactory cf;

  @Test
  public void whenSendMessage_thenReceive() throws JMSException {
    try (final Connection connection = cf.createConnection()) {
      final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      final MessageProducer producer = session.createProducer(destination);
      producer.send(session.createTextMessage(MDBListener.TEXT));

      await()
          .untilAsserted(() -> assertThat(MDBListener.sync()).isTrue());
    }
  }

  @Configuration
  public Properties config() {
    return new PropertiesBuilder()
        .p("amq", "new://Resource?type=ActiveMQResourceAdapter")
        .p("amq.DataSource", "")
        .p("amq.BrokerXmlConfig", "broker:(vm://localhost)")
        .p("target", "new://Resource?type=Queue")
        .p("mdbs", "new://Container?type=MESSAGE")
        .p("mdbs.ResourceAdapter", "amq")
        .p("cf", "new://Resource?type=" + ConnectionFactory.class.getName())
        .p("cf.ResourceAdapter", "amq")
        .build();
  }

  @Module
  public EjbJar beans() {
    EjbJar ejbJar = new EjbJar("timer-beans");
    ejbJar.addEnterpriseBean(new MessageDrivenBean(MDBListener.class));
    return ejbJar;
  }
}