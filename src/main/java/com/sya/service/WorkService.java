package com.sya.service;

import com.sya.dao.WorkDAO;
import com.sya.model.Takes;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.CreateWorkRequest;
import com.sya.view.WorkListView;
import com.sya.view.WorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    public Work getWorkById(Integer workId) {
        Optional<Work> optionalWork=workDAO.findById(workId);
        if(!optionalWork.isPresent()) {
            return null;
        }
        return optionalWork.get();
    }

    public Integer getOwnWorkByPage(Integer pageNum,Integer pageSize,User student,List<Work> workList) {
       Set<Takes> takesSet=student.getTakesSet();
       if(takesSet.isEmpty()) return 0;
       WorkListView workListView=new WorkListView();
       workListView.setPageNum(pageNum);
       workListView.setTotalPage(takesSet.size());
       int offset=(pageSize-1)*pageNum;
       int count=0;
       for(Takes takes: takesSet){
           count++;
           if(count<=offset) {
               continue;
           }
           if(count>offset+pageSize){
               break;
           }
           workList.add(takes.getWork());
       }
       return 1+takesSet.size()/pageSize;

    }

}
