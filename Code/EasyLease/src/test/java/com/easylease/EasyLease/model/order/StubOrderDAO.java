package com.easylease.EasyLease.model.order;

import com.easylease.EasyLease.control.utility.exception.EntityTamperingException;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.Estimate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StubOrderDAO implements OrderDAO {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
  private List<Order> ordini = new ArrayList<Order>() {{
    try {
      add(new Order("OR1ER4T",
          new Estimate("EShKs85", 341, new Client("CLEE8BD", "Mattia", "Caprio",
              "mattia.caprio@unisa.com", "Avellino",
              sdf.parse("1998-09-08"), "Uomo", "Patierno", "83020",
              "Via Nazionale"), new Advisor("ADJdybc", "Clementina", "Rossa",
              "rossa.clementina@frutta.com", sdf.parse("2020-08-12")),
              new Car(),
              24, null, false, "Confermato", sdf.parse("2018-12-22"),
              sdf.parse("2018-12-22")), sdf.parse("2018-12-29"),
          sdf.parse("2020-12-29"), sdf.parse("2018-12-29"),
          sdf.parse("2018-12-22"), true, "Completato"));
      add(new Order("ORlk7Bn",
          new Estimate("ESH6f5E", 523,
              new Client("CLcapNK", "Gebiveffa", "Alticuori",
                  "geno.alti@unisa.com", "Atripalda",
                  sdf.parse("1994-12-23"), "Donna", "Pongi Ponzi", "90909",
                  "Via Delle Cotrecchie"),
              new Advisor("ADJdybc", "Clementina", "Rossa",
                  "rossa.clementina@frutta.com", sdf.parse("2020-08-12")),
              new Car(),
              18, null, false, "Confermato", sdf.parse("2018-12-22"),
              sdf.parse("2020-08-04")), sdf.parse("2020-08-08"),
          null, null,
          sdf.parse("2020-08-04"), true, "Attesa"));
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }};

  @Override
  public Order retrieveById(String id) {
    for (Order o : ordini) {
      if (o.getId().equals(id))
        return o;
    }
    return null;
  }

  @Override
  public List<Order> retrieveByAdvisor(String id) {
    List<Order> orders = new ArrayList<>();
    for (Order o : ordini) {
      if (o.getEstimate().getAdvisor().getId().equals(id))
        orders.add(o);
    }
    return orders;
  }

  @Override
  public List<Order> retrieveByClient(String id) {
    List<Order> orders = new ArrayList<>();
    for (Order o : ordini) {
      if (o.getEstimate().getClient().getId().equals(id))
        orders.add(o);
    }
    return orders;
  }

  @Override
  public List<Order> retrieveAll() {
    return ordini;
  }

  @Override
  public void update(Order order) throws EntityTamperingException {
    if (order == null) {
      throw new EntityTamperingException("Order does not exist!");
    }
    for (Order o : ordini) {
      if (o.getId().equals(order.getId())) {
        o.setEstimate(order.getEstimate());
        o.setStartDate(order.getStartDate());
        o.setEndDate(order.getEndDate());
        o.setPickupDate(order.getPickupDate());
        o.setCreationDate(order.getCreationDate());
        o.setVisibility(order.isVisibility());
        o.setState(order.getState());
        break;
      }
    }
  }

  @Override
  public void insert(Order order) throws EntityTamperingException {
    if (!ordini.contains(order)) {
      ordini.add(order);
    } else {
      throw new EntityTamperingException("Already existing Order!");
    }
  }

  @Override
  public void delete(Order order) throws EntityTamperingException {
    if (ordini.contains(order)) {
      ordini.remove(order);
    } else {
      throw new EntityTamperingException("Order does not exist!");
    }
  }
}
