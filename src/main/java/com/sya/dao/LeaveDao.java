package com.sya.dao;

import com.sya.model.LeaveInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface LeaveDao extends CrudRepository<LeaveInformation,Integer> {
    Page<LeaveInformation> findAllByStudentId(Integer studentId, Pageable pageable);

}
