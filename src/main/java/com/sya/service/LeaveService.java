package com.sya.service;

import com.sya.common.Pagination;
import com.sya.dao.LeaveDao;
import com.sya.dao.TakesDAO;
import com.sya.dao.WorkDAO;
import com.sya.model.LeaveInformation;
import com.sya.model.Takes;
import com.sya.model.User;
import com.sya.request.LeaveManagementRequest;
import com.sya.request.LeaveRequset;
import com.sya.request.LeaveUpdateRequest;
import com.sya.view.LeaveItem;
import com.sya.view.LeaveItemsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class LeaveService {
    @Autowired
    LeaveDao leaveDao;
    @Autowired
    WorkDAO workDAO;
    @Autowired
    TakesDAO takesDAO;

    @Transactional
    public LeaveItem createLeave(LeaveRequset leaveRequset, User user){
        if(takesDAO.findTakesByStudentAndWork(user,workDAO.findWorkById(leaveRequset.getWorkId()))==null) {
            return null;
        }
        LeaveInformation leaveInformation = new LeaveInformation();
        leaveInformation.setWork(workDAO.findWorkById(leaveRequset.getWorkId()));
        leaveInformation.setStudent(user);
        leaveInformation.setContent(leaveRequset.getContent());
        leaveInformation.setProof(leaveRequset.getProof());
        leaveInformation.setStatus(0);
        leaveInformation.setRequestTime(new Date());
        leaveInformation.setLeaveDay(leaveRequset.getLeaveDay());
        leaveInformation.setLeaveStart(leaveRequset.getLeaveStart());
        leaveInformation.setLeaveEnd(leaveRequset.getLeaveEnd());
        leaveDao.save(leaveInformation);
        return new LeaveItem(leaveInformation);
    }

    public LeaveItemsPage proViewLeaves(Pagination pagination, User user){
        return new LeaveItemsPage(leaveDao.findByWork_Teacher(user, PageRequest.of(pagination.getPageNum() - 1, pagination.getPageSize())));
    }

    public LeaveItemsPage viewLeaves(Pagination pagination, User user){
        return new LeaveItemsPage(leaveDao.findAllByStudentId(user.getId(), PageRequest.of(pagination.getPageNum() - 1, pagination.getPageSize())));
    }

    @Transactional
    public LeaveItem updateLeave(LeaveUpdateRequest leaveUpdateRequest, User user){
        LeaveInformation leaveInformation = checkLeave(leaveUpdateRequest.getLeaveId(),user);

        if(leaveInformation == null){
            return null;
        }

        leaveInformation.setWork(workDAO.findWorkById(leaveUpdateRequest.getWorkId()));
        leaveInformation.setContent(leaveUpdateRequest.getContent());
        leaveInformation.setProof(leaveUpdateRequest.getProof());
        leaveInformation.setLeaveDay(leaveUpdateRequest.getLeaveDay());
        leaveInformation.setLeaveStart(leaveUpdateRequest.getLeaveStart());
        leaveInformation.setLeaveEnd(leaveUpdateRequest.getLeaveEnd());
        leaveDao.save(leaveInformation);
        return new LeaveItem(leaveInformation);
    }

    @Transactional
    public LeaveItem teacherUpdateLeave(LeaveManagementRequest leaveManagementRequest){
        Optional<LeaveInformation> optionalLeaveInformation = leaveDao.findById(leaveManagementRequest.getLeaveId());

        if(!optionalLeaveInformation.isPresent()){
            return null;
        }

        LeaveInformation leaveInformation = optionalLeaveInformation.get();

        leaveInformation.setStatus(leaveManagementRequest.getStatus());
        leaveDao.save(leaveInformation);
        return new LeaveItem(leaveInformation);
    }

    @Transactional
    public Integer deleteLeave(LeaveManagementRequest leaveManagementRequest, User user){
        LeaveInformation leaveInformation = checkLeave(leaveManagementRequest.getLeaveId(),user);

        if(leaveInformation == null){
            return null;
        }

        leaveDao.delete(leaveInformation);
        return 1;
    }

    private LeaveInformation checkLeave(Integer leaveId, User user){
        Optional<LeaveInformation> optionalLeaveInformation = leaveDao.findById(leaveId);

        if(!optionalLeaveInformation.isPresent()){
            return null;
        }

        LeaveInformation leaveInformation = optionalLeaveInformation.get();

        if(!leaveInformation.getStudent().getId().equals(user.getId())){
            return null;
        }

        return leaveInformation;
    }
}
