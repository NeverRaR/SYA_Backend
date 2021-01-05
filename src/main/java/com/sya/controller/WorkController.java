package com.sya.controller;

import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.CreateWorkRequest;
import com.sya.request.RegisterRequest;
import com.sya.request.ViewWorkInfoRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.WorkService;
import com.sya.view.AccountStatus;
import com.sya.view.Message;
import com.sya.view.WorkStatus;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/Work")
public class WorkController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    WorkService workService;

    @PostMapping(path = "/CreateWork")
    public @ResponseBody
    Object CreateWork (@RequestBody CreateWorkRequest body, @CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId) {
        User user=authenticationService.getUser(sessionId);
        if(user.getRole().equals(1)) {
            return new Message("Student cannot create work");
        }
        if(body.getStartTime().equals(body.getEndTime())){
            return new Message("Duration time is 0.");
        }
        Work work=workService.addNewWork(body,user.getId());
        return getWorkStatus(work);
    }

    @GetMapping(path = "/ViewWorkInfo")
    public @ResponseBody
    Object ViewWorkInfo (@RequestBody ViewWorkInfoRequest body) {
        return getWorkStatus(workService.getWork(body.getWorkId()));
    }

    private WorkStatus getWorkStatus(Work work){
        WorkStatus workStatus=new WorkStatus();
        if(work ==null){
            return workStatus;
        }
        workStatus.setId(work.getId());
        workStatus.setAddress(work.getAddress());
        workStatus.setWorkDescription(work.getDescription());
        workStatus.setWorkName(work.getName());
        workStatus.setCollectNum(work.getCollectNum());
        workStatus.setCover(work.getCover());
        workStatus.setEndDay(work.getEndDay());
        workStatus.setEndTime(work.getEndTime());
        workStatus.setLikesNum(work.getLikesNum());
        workStatus.setSalary(work.getSalary());
        workStatus.setStartDay(work.getStartDay());
        workStatus.setStartTime(work.getStartTime());
        workStatus.setTotalTime(work.getTotalTime());
        workStatus.setWeekDay(work.getWeekDay());
        return workStatus;

    }
}
