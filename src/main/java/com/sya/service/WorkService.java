package com.sya.service;

import com.sya.dao.WorkDAO;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.CreateWorkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkService {
    @Autowired
    WorkDAO workDAO;

    @Transactional
    public Work addNewWork(CreateWorkRequest body, Integer teacherId) {
        Work work = new Work();
        work.setAddress(body.getAddress());
        work.setCover(body.getCover());
        work.setDescription(body.getWorkDescription());
        work.setName(body.getWorkName());
        work.setEndDay(body.getEndDay());
        work.setEndTime(body.getEndTime());
        work.setSalary(body.getSalary());
        work.setStartDay(body.getStartDay());
        work.setStartTime(body.getStartTime());
        work.setWeekDay(body.getWeekDay());
        User user=new User();
        user.setId(teacherId);
        work.setTeacher(user);
        workDAO.save(work);
        return work;
    }

}
