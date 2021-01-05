package com.sya.service;

import com.sya.dao.WorkDAO;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.CreateWorkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        work.setLikesNum(0);
        work.setCollectNum(0);
        return work;
    }

    public Work getWork(Integer workId) {
        Optional<Work> optionalWork=workDAO.findById(workId);
        if(!optionalWork.isPresent()) {
            return null;
        }
        return optionalWork.get();
    }

}
