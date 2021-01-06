package com.sya.service;

import com.sya.dao.LeaveDao;
import com.sya.dao.WorkDAO;
import com.sya.model.LeaveInformation;
import com.sya.model.User;
import com.sya.request.LeaveRequset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaveService {
    @Autowired
    LeaveDao leaveDao;
    @Autowired
    WorkDAO workDAO;

    @Transactional
    public LeaveRequset CreateLeave(LeaveRequset leaveRequset, User user){
        LeaveInformation leaveInformation = new LeaveInformation();
        

    }
}
