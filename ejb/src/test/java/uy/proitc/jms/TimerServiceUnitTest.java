package uy.proitc.jms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.Is.is;

import javax.ejb.EJB;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.SingletonBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ApplicationComposer.class)
public class TimerServiceUnitTest {

  @EJB
  private TimerService timerService;

  @Test
  public void whenGetCounter_thenValueGtZero() throws InterruptedException {
    await().untilAsserted(() -> assertThat(timerService.getCounter()).isPositive());
  }

  @Module
  public EjbJar beans() {
    EjbJar ejbJar = new EjbJar("timer-beans");
    ejbJar.addEnterpriseBean(new SingletonBean(TimerService.class));
    return ejbJar;
  }
}
