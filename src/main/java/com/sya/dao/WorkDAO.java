package com.sya.dao;

import com.sya.model.User;
import com.sya.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkDAO extends CrudRepository<Work,Integer> {
    Page<Work> findAll(Pageable pageable);

    @Query(value = "select count(*) from work",nativeQuery = true)
    Integer findNum();

    @Query(value = "select count(*) from takes where ")
}
