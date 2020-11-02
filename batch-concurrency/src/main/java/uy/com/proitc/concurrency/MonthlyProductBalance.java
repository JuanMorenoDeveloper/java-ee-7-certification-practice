package uy.com.proitc.concurrency;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;

public class MonthlyProductBalance implements Callable<Double>, ManagedTaskListener, ManagedTask {

  private static final Logger LOG = Logger.getLogger(MonthlyProductBalance.class.getName());
  private final List<Product> products;
  private boolean isDone = false;

  public MonthlyProductBalance(List<Product> products) {
    this.products = products;
  }

  @Override
  public Double call() throws Exception {
    LOG.info("Doing some calculations");
    Thread.sleep(200);
    return products.stream().mapToDouble(Product::getRevenue).sum();
  }

  @Override
  public void taskSubmitted(Future<?> future, ManagedExecutorService managedExecutorService,
      Object o) {
    LOG.info("taskSubmitted");
  }

  @Override
  public void taskAborted(Future<?> future, ManagedExecutorService managedExecutorService, Object o,
      Throwable throwable) {
    LOG.info("taskAborted");
  }

  @Override
  public void taskDone(Future<?> future, ManagedExecutorService managedExecutorService, Object o,
      Throwable throwable) {
    LOG.info("taskDone");
    isDone = true;
  }

  @Override
  public void taskStarting(Future<?> future, ManagedExecutorService managedExecutorService,
      Object o) {
    LOG.info("taskStarting");
  }

  public boolean isDone() {
    return isDone;
  }

  @Override
  public ManagedTaskListener getManagedTaskListener() {
    return this;
  }

  @Override
  public Map<String, String> getExecutionProperties() {
    return Collections.emptyMap();
  }
}
