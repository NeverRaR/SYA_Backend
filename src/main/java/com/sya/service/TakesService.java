package com.sya.service;

import com.sya.dao.ApplyDAO;
import com.sya.dao.TakesDAO;
import com.sya.model.Apply;
import com.sya.model.Takes;
import com.sya.model.pk.TakesPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TakesService {
    @Autowired
    TakesDAO takesDAO;

    @Autowired
    ApplyDAO applyDAO;

    @Transactional
    public Integer resign(Integer studentId,Integer workId){
        TakesPK takesPK=new TakesPK(studentId,workId);
        Optional<Takes> optionalTakes=takesDAO.findById(takesPK);
        if(!optionalTakes.isPresent()) {
            return 0;
        }
        optionalTakes.get().setStatus(1);
        takesDAO.save(optionalTakes.get());
        Apply apply=applyDAO.findByStudentAndWork(optionalTakes.get().getStudent(),optionalTakes.get().getWork());
        if(apply != null) {
            applyDAO.delete(apply);
        }
        return 1;
    }


    public Takes getTakes(Integer studentId,Integer workId){
        TakesPK takesPK=new TakesPK(studentId,workId);
        Optional<Takes> optionalTakes=takesDAO.findById(takesPK);
        if(!optionalTakes.isPresent()) {
            return null;
        }
        return optionalTakes.get();
    }

    @Transactional
    public Integer deleteResign(Integer studentId,Integer workId){
        TakesPK takesPK=new TakesPK(studentId,workId);
        Optional<Takes> optionalTakes=takesDAO.findById(takesPK);
        if(!optionalTakes.isPresent()) {
            return 1;
        }
        if(optionalTakes.get().getStatus().equals(0)){
            return 0;
        }
        takesDAO.delete(optionalTakes.get());
        return 1;
    }

}
