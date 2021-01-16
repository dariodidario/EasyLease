package com.easylease.EasyLease.control.utility.scheduler;

import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDAO;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonthChecker implements Runnable {
  @Override
  public void run() {
    OrderDAO orderDAO = DBOrderDAO.getInstance();
    EstimateDAO estimateDAO = DBEstimateDAO.getInstance();
    Calendar c = Calendar.getInstance();

    /*
     * Part1: Check that the orders in the "Non confermato" state do not have
     * the "creation_date" older than 30 days.
     */
    List<Order> orderList = orderDAO.retrieveByState("Non confermato");
    orderList.forEach(order -> {
      c.setTime(order.getCreationDate());
      c.add(Calendar.MONTH, 1);
      if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
        orderDAO.delete(order);
      }
    });
    Logger.getLogger(DailyChecker.class.getName())
        .log(Level.INFO,
            "Monthly: Controllo sugli Order (Non confermato) effettuato!");

    /*
     * Part2: Check that the orders in the "Non pagato" state do not have
     * the "confirm_date" older than 30 days.
     */
    orderList = orderDAO.retrieveByState("Non pagato");
    orderList.forEach(order -> {
      c.setTime(order.getConfirmDate());
      c.add(Calendar.MONTH, 1);
      if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
        orderDAO.delete(order);
      }
    });
    Logger.getLogger(DailyChecker.class.getName())
        .log(Level.INFO,
            "Monthly: Controllo sugli Order (Non pagato) effettuato!");

    /*
     * Part3: Check that the Estimate in the "Non Confermato" state do not have
     * the "response_date" older than 7 days.
     */
    List<Estimate> estimateList = estimateDAO.retrieveByState("Non confermato");
    estimateList.forEach(estimate -> {
      c.setTime(estimate.getResponseDate());
      c.add(Calendar.MONTH, 1);
      if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
        estimateDAO.delete(estimate);
      }
    });
    Logger.getLogger(DailyChecker.class.getName())
        .log(Level.INFO,
            "Monthly: Controllo sugli Estimate (Non Confermato) effettuato!");
  }
}
