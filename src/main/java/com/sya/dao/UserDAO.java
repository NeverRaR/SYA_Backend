package com.sya.dao;

import com.sya.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User,Integer> {

    User findByUsername(String username);

    List<User> findAllByRole(Integer role);
}
