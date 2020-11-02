package uy.com.proitc.concurrency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.awaitility.Awaitility.await;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MonthlyProductBalanceUnitTest {

  @Inject
  private MonthlyProductBalanceManager manager;

  @Deployment
  public static WebArchive app() {
    return ShrinkWrap.create(WebArchive.class)
        .addClasses(MonthlyProductBalance.class, MonthlyProductBalanceManager.class);
  }

  @Test
  public void whenExecuteMyTask_thenGetResult() {
    manager.initTask();
    var task = manager.getBalance();

    await().untilAsserted(() -> assertThat(task.isDone()).isTrue());
    assertThat(manager.getResult()).isEqualTo(50000.0d, within(0.1d));
  }
}
