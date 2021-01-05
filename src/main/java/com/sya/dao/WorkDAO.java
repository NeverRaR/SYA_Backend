package com.sya.dao;

import com.sya.model.User;
import com.sya.model.Work;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkDAO extends CrudRepository<Work,Integer> {

}
