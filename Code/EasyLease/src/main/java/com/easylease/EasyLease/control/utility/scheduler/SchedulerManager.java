package com.easylease.EasyLease.control.utility.scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class SchedulerManager implements ServletContextListener {
  private ScheduledExecutorService scheduler;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    this.scheduler = Executors.newSingleThreadScheduledExecutor();
    this.scheduler.scheduleAtFixedRate(new DailyChecker(), 1, 1, TimeUnit.DAYS);
    this.scheduler.scheduleAtFixedRate(new MonthChecker(), 1, 31, TimeUnit.DAYS);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    this.scheduler.shutdownNow();
  }
}
