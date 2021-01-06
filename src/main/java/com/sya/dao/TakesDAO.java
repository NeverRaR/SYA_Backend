package com.sya.dao;

import com.sya.model.Takes;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.model.pk.TakesPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TakesDAO extends CrudRepository<Takes, TakesPK> {
    Takes findTakesByStudentAndWork(User user, Work work);

    @Query(value="select sum(work_time) from takes where student_id=?1",nativeQuery = true)
    Double findWorkTime(Integer studentId);

    @Query(value="select sum(absent_num) from takes where student_id=?1",nativeQuery = true)
    Integer findAbsentNum(Integer studentId);

    @Query(value="select sum(absent_time) from takes where student_id=?1",nativeQuery = true)
    Double findAbsentTime(Integer studentId);


    @Query(value="select status from takes where student_id=?1 " +
            "and work_id in (select work_id from work where work_name like CONCAT('%',?2,'%') " +
            "or work_description like CONCAT('%',?2,'%') " +
            "or address like CONCAT('%',?2,'%'))",nativeQuery = true)
    List<Integer> findOwnWorkStatus(Integer studentId,String query,Integer offset,Integer pageSize);
}
