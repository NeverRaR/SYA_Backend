package com.sya.dao;

import com.sya.model.Apply;
import com.sya.model.MessageLibrary;
import com.sya.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ApplyDAO extends CrudRepository<Apply,Integer> {

    @Query(value = "select status from apply " +
            "where apply.student_id=:studentId " +
            "and apply.work_id=:WorkId",nativeQuery = true)
    Integer findStatus(Integer studentId, Integer WorkId);

    Apply findApplyById(Integer Id);

    Integer countAllByStudent(User student);

    Page<Apply> findAllByTeacher(User user, Pageable pageable);

}
