package com.easylease.EasyLease.model.estimate;

import java.util.List;

public interface EstimateDAO {

  Estimate retrieveById(String id);

  List<Estimate>  retrieveAll();

  List<Estimate> retrieveByAdvisor(String id);

  List<Estimate> retrieveByClient(String id);

  void update(Estimate e);

  void delete(Estimate e);

  void insert(Estimate e);

}
