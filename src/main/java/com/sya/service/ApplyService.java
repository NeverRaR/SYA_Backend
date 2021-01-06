package com.sya.service;

import com.sya.common.Pagination;
import com.sya.dao.ApplyDAO;
import com.sya.dao.TakesDAO;
import com.sya.dao.WorkDAO;
import com.sya.model.*;
import com.sya.request.ProManageDto;
import com.sya.view.ApplyInfo;
import com.sya.view.ApplyInfoPage;
import com.sya.view.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplyService {


    @Autowired
    ApplyDAO applyDAO;

    @Autowired
    WorkDAO workDAO;

    @Autowired
    TakesDAO takesDAO;

    @Transactional
    public Integer CreateApply(User user, Integer workId){

        Work work = workDAO.findWorkById(workId);
        Resume resume = user.getStudentResume();
        if(work == null) return null;
        if(resume == null) return -10;

        //返回的Status有三种状态：
        //正在申请 : 0
        //已录用 : 1
        //落选 : 2
        Integer status = applyDAO.findStatus(user.getId(),workId);

        if(status == null || status == 2){
            Apply apply = new Apply();
            apply.setTeacher(work.getTeacher());
            apply.setResume(resume);
            apply.setStudent(user);
            apply.setWork(work);
            apply.setStatus(0);
            applyDAO.save(apply);
            if(apply.getId()==null)return -1;
            else return apply.getId();
        }
        else if(status == 0){
            return -22;
        }
        else if(status == 1){
            return -21;
        }
        else {//无status和落选状态时：
            return null;
        }
    }

    public ApplyInfoPage ProViewApps(User teacher, Pagination pagination){
        return new ApplyInfoPage(applyDAO.findAllByTeacher(teacher, PageRequest.of(pagination.getPageNum() -1 ,
                pagination.getPageSize())));
    }

    @Transactional
    public Object ProManageApp(ProManageDto proManageDto){

        if((!proManageDto.getStatus().equals(1))&&
                (!proManageDto.getStatus().equals(2)))
            return new Message("It's illegal operation!");

        Apply apply = applyDAO.findApplyById(proManageDto.getApplyId());

        if(apply==null) return new Message("Apply id not found.");

        if(!apply.getStatus().equals(0)) return new Message("Application have been managed.");

        if(proManageDto.getStatus().equals(2)){
            apply.setStatus(2);
            applyDAO.save(apply);
            return new ApplyInfo(apply);
        }
        else if (proManageDto.getStatus().equals(1)){
            apply.setStatus(1);
            applyDAO.save(apply);
            Takes take = new Takes();
            take.setAbsentTime(0.0);
            take.setAbsentNum(0);
            take.setWork(apply.getWork());
            take.setWorkTime(apply.getWork().getTotalTime());
            take.setStudent(apply.getStudent());
            take.setStatus(0);
            takesDAO.save(take);
            return new ApplyInfo(apply);
        }
        return new Message("It's illegal operation!");
    }
}
