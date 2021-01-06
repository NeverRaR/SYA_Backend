package com.sya.dao;

import com.sya.model.LeaveInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface LeaveDao extends CrudRepository<LeaveInformation,Integer> {
    Page<LeaveInformation> findAllByStudentId(Integer studentId, Pageable pageable);

    @Query(value = "select * from leave_information " +
            "where leave_id in " +
            "select leave_id from leave_information, work " +
            "where work.work_id = leave_information.work_id and work.teacher_id = ?1 ",nativeQuery = true)
    Page<LeaveInformation> findTeacherLeave(Integer teacherId, Pageable pageable);

}
