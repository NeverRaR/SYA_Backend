package com.sya.service;

import com.sya.dao.WorkDAO;
import com.sya.model.Takes;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.CreateWorkRequest;
import com.sya.view.WorkListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Integer totalWork=0;
        totalWork=workDAO.findOwnNum(student.getId());
        if(totalWork.equals(0)) {
            return 0;
        }
        Iterable<Work> workIterable= workDAO.findAllById(workDAO.findOwnWork(student.getId(),(pageNum-1)*pageSize,pageSize));
        for(Work work: workIterable){
            workList.add(work);
        }
        return 1+(totalWork-1)/pageSize;

    }

    public Integer getAllWorkByPage(Integer pageNum,Integer pageSize,List<Work> workList){
        Sort sort=Sort.by("id");
        Pageable pageable= PageRequest.of(pageNum-1,pageSize,sort);
        Page<Work> workPage=workDAO.findAll(pageable);
        for(Work work:workPage){
            workList.add(work);
        }
        Integer allWork=workDAO.findAllNum();
        if(allWork.equals(0)) {
            return 0;
        }
        return (allWork-1)/pageSize+1;
    }

    public Integer getHistoryWorkByPage(Integer pageNum,Integer pageSize,User teacher,List<Work> workList){
        Integer totalWork=0;
        totalWork=workDAO.findHistoryNum(teacher.getId());
        if(totalWork.equals(0)) {
            return 0;
        }
        Iterable<Work> workIterable= workDAO.findAllById(workDAO.findHistoryWork(teacher.getId(),(pageNum-1)*pageSize,pageSize));
        for(Work work: workIterable){
            workList.add(work);
        }
        return 1+(totalWork-1)/pageSize;
    }

    public Integer findOwnWorkByPage(Integer pageNum,Integer pageSize,User student,List<Work> workList,String query) {
        Integer totalWork=0;
        totalWork=workDAO.findOwnNum(student.getId(),query);
        if(totalWork.equals(0)) {
            return 0;
        }
        Iterable<Work> workIterable= workDAO.findAllById(workDAO.findOwnWork(student.getId(),query,(pageNum-1)*pageSize,pageSize));
        for(Work work: workIterable){
            workList.add(work);
        }
        return 1+(totalWork-1)/pageSize;
    }

    public Integer findAllWorkByPage(Integer pageNum,Integer pageSize,List<Work> workList,String query) {

        Integer totalWork=0;
        totalWork=workDAO.findAllNum(query);
        if(totalWork.equals(0)) {
            return 0;
        }
        Iterable<Work> workIterable= workDAO.findAllById(workDAO.findAllWork((pageNum-1)*pageSize,pageSize,query));
        for(Work work: workIterable){
            workList.add(work);
        }
        return 1+(totalWork-1)/pageSize;
    }



}
