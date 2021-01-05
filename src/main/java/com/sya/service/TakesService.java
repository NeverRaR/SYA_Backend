package com.sya.service;

import com.sya.dao.TakesDAO;
import com.sya.model.Takes;
import com.sya.model.pk.TakesPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TakesService {
    @Autowired
    TakesDAO takesDAO;

    public Integer resign(Integer studentId,Integer workId){
        TakesPK takesPK=new TakesPK(studentId,workId);
        Optional<Takes> optionalTakes=takesDAO.findById(takesPK);
        if(!optionalTakes.isPresent()) {
            return 0;
        }
        optionalTakes.get().setStatus(1);
        return 1;

    }
}
