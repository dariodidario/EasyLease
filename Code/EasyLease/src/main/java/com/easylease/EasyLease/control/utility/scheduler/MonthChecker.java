package com.easylease.EasyLease.control.utility.scheduler;

import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDao;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDao;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonthChecker implements Runnable {
  @Override
  public void run() {
    OrderDao orderDAO = DbOrderDao.getInstance();
    EstimateDao estimateDAO = DbEstimateDao.getInstance();
    Calendar c = Calendar.getInstance();

    /*
     * Part1: Check that the orders in the "Non confermato" state do not have
     * the "creation_date" older than 30 days.
     */
    List<Order> orderList = orderDAO.retrieveByState("Non confermato");
    if (orderList != null) {
      orderList.forEach(order -> {
        c.setTime(order.getCreationDate());
        c.add(Calendar.MONTH, 1);
        if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
          orderDAO.delete(order);
        }
      });
      Logger.getLogger(MonthChecker.class.getName())
          .log(Level.INFO,
              "Monthly: Controllo sugli Order (Non confermato) effettuato!");
    }

    /*
     * Part2: Check that the orders in the "Non pagato" state do not have
     * the "confirm_date" older than 30 days.
     */
    orderList = orderDAO.retrieveByState("Non pagato");
    if (orderList != null) {
      orderList.forEach(order -> {
        c.setTime(order.getConfirmDate());
        c.add(Calendar.MONTH, 1);
        if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
          orderDAO.delete(order);
        }
      });
      Logger.getLogger(MonthChecker.class.getName())
          .log(Level.INFO,
              "Monthly: Controllo sugli Order (Non pagato) effettuato!");
    }

    /*
     * Part3: Check that the Estimate in the "Non Confermato" state do not have
     * the "response_date" older than 7 days.
     */
    List<Estimate> estimateList = estimateDAO.retrieveByState("Non confermato");
    if (estimateList != null) {
      estimateList.forEach(estimate -> {
        c.setTime(estimate.getResponseDate());
        c.add(Calendar.MONTH, 1);
        if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
          estimateDAO.delete(estimate);
        }
      });
      Logger.getLogger(MonthChecker.class.getName())
          .log(Level.INFO,
              "Monthly: Controllo sugli Estimate (Non Confermato) effettuato!");
    }
  }
}
