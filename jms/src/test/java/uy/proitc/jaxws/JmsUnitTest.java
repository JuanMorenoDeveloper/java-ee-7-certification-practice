package uy.proitc.jaxws;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.awaitility.Awaitility.await;

import java.util.Properties;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
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
public class JmsUnitTest {

  @Inject
  JmsProducer producer;

  @Test
  public void whenSendMessage_thenReceive() {
    producer.sendMessage("Test");

    await()
        .untilAsserted(() -> assertThat(JmsConsumer.isReceived()).isTrue());
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
  @Classes(value = JmsProducer.class, cdi = true)
  public EjbJar beans() {
    EjbJar ejbJar = new EjbJar("timer-beans");
    ejbJar.addEnterpriseBean(new MessageDrivenBean(JmsConsumer.class));
    return ejbJar;
  }
}