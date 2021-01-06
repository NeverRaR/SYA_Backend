package com.sya.dao;

import com.sya.model.Takes;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.model.pk.TakesPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TakesDAO extends CrudRepository<Takes, TakesPK> {
    Takes findTakesByStudentAndWork(User user, Work work);

    @Query(value="select sum(work_time) from takes where student_id=?1",nativeQuery = true)
    Double findWorkTime(Integer studentId);

    @Query(value="select sum(absent_num) from takes where student_id=?1",nativeQuery = true)
    Integer findAbsentNum(Integer studentId);

    @Query(value="select sum(absent_time) from takes where student_id=?1",nativeQuery = true)
    Double findAbsentTime(Integer studentId);




}
