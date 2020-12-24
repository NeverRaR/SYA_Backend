package com.sya.dao;

import com.sya.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User,Integer> {

}
