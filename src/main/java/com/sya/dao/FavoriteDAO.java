package com.sya.dao;

import com.sya.model.Favorite;
import com.sya.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FavoriteDAO extends CrudRepository<Favorite, Integer> {

    Favorite findByName(String name);
}
