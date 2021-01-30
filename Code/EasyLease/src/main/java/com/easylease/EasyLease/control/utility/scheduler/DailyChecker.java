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

public class DailyChecker implements Runnable {
  @Override
  public void run() {
    OrderDao orderDAO = DbOrderDao.getInstance();
    EstimateDao estimateDAO = DbEstimateDao.getInstance();
    Calendar c = Calendar.getInstance();

    /*
     * Part1: Check that the orders in the "Attesa" state do not have
     * the "creation_date" older than 7 days.
     */
    List<Order> orderList = orderDAO.retrieveByState("Attesa");
    if (orderList != null) {
      orderList.forEach(order -> {
        c.setTime(order.getCreation_date());
        c.add(Calendar.WEEK_OF_YEAR, 1);
        if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
          order.setVisibility(false);
          order.setState("Non confermato");
        }
      });
      orderList.forEach(orderDAO::update);
      Logger.getLogger(DailyChecker.class.getName())
          .log(Level.INFO,
              "Daily: Controllo sugli Ordini (Attesa) effettuato!");
    }

    /*
     * Part2: Check that the estimate in the "Stipulato" state do not have
     * the "response_date" older than 7 days.
     */
    List<Estimate> estimateList = estimateDAO.retrieveByState("Stipulato");
    if (estimateList != null) {
      estimateList.forEach(estimate -> {
        c.setTime(estimate.getResponse_date());
        c.add(Calendar.WEEK_OF_YEAR, 1);
        if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
          estimate.setVisibility(false);
          estimate.setState("Non confermato");
        }
      });
      estimateList.forEach(estimateDAO::update);
      Logger.getLogger(DailyChecker.class.getName())
          .log(Level.INFO,
              "Daily: Controllo sugli Estimate (Stipulato) effettuato!");
    }

    /*
     * Part3: Check that the Order in the "Confermato" state do not have
     * the "confirm_date" older than 7 days.
     */
    orderList = orderDAO.retrieveByState("Confermato");
    if (orderList != null) {
      orderList.forEach(order -> {
        c.setTime(order.getConfirm_date());
        c.add(Calendar.WEEK_OF_YEAR, 1);
        if (c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
          order.setVisibility(false);
          order.setState("Non pagato");
        }
      });
      orderList.forEach(orderDAO::update);
      Logger.getLogger(DailyChecker.class.getName())
          .log(Level.INFO,
              "Daily: Controllo sugli Ordini (Confermato) effettuato!");
    }
  }
}
