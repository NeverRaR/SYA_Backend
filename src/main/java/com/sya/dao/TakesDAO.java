package com.sya.dao;

import com.sya.model.Takes;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.model.pk.TakesPK;
import org.springframework.data.repository.CrudRepository;

public interface TakesDAO extends CrudRepository<Takes, TakesPK> {
    Takes findTakesByStudentAndWork(User user, Work work);

}
