package com.sya.dao;

import com.sya.model.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SalaryDAO extends CrudRepository<Salary, Integer> {

    @Query(value="select sum(num) from salary where student_id=?1",nativeQuery = true)
    Double findIncome(Integer studentId);
}
