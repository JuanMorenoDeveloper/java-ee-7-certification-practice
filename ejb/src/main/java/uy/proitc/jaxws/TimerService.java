package uy.proitc.jaxws;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
@Lock(LockType.READ)
public class TimerService {

  Class clazz;
  private static final Logger LOG = Logger.getLogger(TimerService.class.getName());
  private final AtomicInteger counter = new AtomicInteger(0);

  @Schedule(second = "*", minute = "*", hour = "*")
  public void scheduleTask() {
    LOG.info(String.format("Current counter: %d", counter.get()));
    counter.incrementAndGet();
  }

  public int getCounter() {
    return counter.get();
  }
}
