package com.sya.dao;

import com.sya.model.Takes;
import com.sya.model.pk.TakesPK;
import org.springframework.data.repository.CrudRepository;

public interface TakesDAO extends CrudRepository<Takes, TakesPK> {


}
