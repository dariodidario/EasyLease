package com.easylease.EasyLease.model.optional;

import java.util.List;

public interface OptionalDAO {

  Optional retrieveById(String id);

  List<Optional> retrieveByType(String type);

}
