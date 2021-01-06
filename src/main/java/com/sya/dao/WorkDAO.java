package com.sya.dao;

import com.sya.model.Takes;
import com.sya.model.User;
import com.sya.model.Favorite;
import com.sya.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface WorkDAO extends CrudRepository<Work, Integer> {

    List<Work> findAllByFavoriteHasWorks_Favorite(Favorite favorite);

    Page<Work> findAll(Pageable pageable);

    @Query(value = "select count(*) from work",nativeQuery = true)
    Integer findAllNum();

    @Query(value = "select count(*) from takes where student_id = ?1",nativeQuery = true)
    Integer findOwnNum(Integer studentId);

    @Query(value="select work_id from work " +
            "where work_id in (select work_id from takes a where a.student_id=?1) " +
            "and (work_name like CONCAT('%',?2,'%') or work_description like CONCAT('%',?2,'%') " +
            "or address like CONCAT('%',?2,'%'))",nativeQuery = true)
    List<Integer> findOwnWork(Integer studentId,String query,Integer offset,Integer pageSize);

    @Query(value="select work_id from takes a where a.student_id=?1 order by work_id limit ?2,?3" ,nativeQuery = true)
    List<Integer> findOwnWork(Integer studentId,Integer offset,Integer pageSize);

    @Query(value="select work_id from work a where a.teacher_id=?1 order by work_id limit ?2,?3" ,nativeQuery = true)
    List<Integer> findHistoryWork(Integer teacherId,Integer offset,Integer pageSize);

    @Query(value="select count(*) from work a where a.teacher_id=?1" ,nativeQuery = true)
    Integer findHistoryNum(Integer teacherId);

    @Query(value="select count(*) from work " +
            "where work_id in (select work_id from takes a where a.student_id=?1) " +
            "and (work_name like CONCAT('%',?2,'%') or work_description like CONCAT('%',?2,'%') " +
            "or address like CONCAT('%',?2,'%'))"
             ,nativeQuery = true)
    Integer findOwnNum(Integer studentId,String query);

    @Query(value="select work_id from work " +
            "where work_name like CONCAT('%',?3,'%') or work_description like CONCAT('%',?3,'%') " +
            "or address  like CONCAT('%',?3,'%')"+
            "order by work_id limit ?1,?2",nativeQuery = true)
    List<Integer> findAllWork(Integer offset,Integer pageSize,String query);

    @Query(value="select count(*) from work " +
            "where work_name like CONCAT('%',?1,'%') or work_description like CONCAT('%',?1,'%') " +
            "or address like CONCAT('%',?1,'%')"
            ,nativeQuery = true)
    Integer findAllNum(String query);

    Work findWorkById(Integer workId);
}

