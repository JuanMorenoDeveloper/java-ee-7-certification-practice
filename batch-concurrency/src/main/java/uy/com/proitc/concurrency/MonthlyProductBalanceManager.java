package uy.com.proitc.concurrency;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonthlyProductBalanceManager {

  private ManagedExecutorService executorService;

  private MonthlyProductBalance balance;
  private Future<Double> result;


  public MonthlyProductBalanceManager() {
    balance = new MonthlyProductBalance(List
        .of(new Product(1, "Soap", 5000.0d), new Product(2, "Flour", 15000.0d),
            new Product(3, "Juice", 30000.0d)));
  }

  public void initTask() {
    result = executorService.submit(balance);
  }

  @Resource
  public void setExecutorService(ManagedExecutorService executorService) {
    this.executorService = executorService;
  }

  public MonthlyProductBalance getBalance() {
    return balance;
  }

  public double getResult() {
    try {
      return result.get();
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
    }
    return 0;
  }

}
