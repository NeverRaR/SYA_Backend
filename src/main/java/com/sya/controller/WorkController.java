package com.sya.controller;

import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.CreateWorkRequest;
import com.sya.request.PageRequest;
import com.sya.request.ViewWorkInfoRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.WorkService;
import com.sya.view.Message;
import com.sya.view.WorkListView;
import com.sya.view.WorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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
    WorkStatus ViewWorkInfo (@RequestBody ViewWorkInfoRequest body) {
        return getWorkStatus(workService.getWorkById(body.getWorkId()));
    }

    @PostMapping(path = "/ViewOwnWork")
    public @ResponseBody
    Object ViewOwnWork(@RequestBody PageRequest body, @CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId) {
        User student=authenticationService.getUser(sessionId);
        if(student==null) {
            return new Message("sessionId is invalid!");
        }
        Integer totalPage=-1;
        List<Work> workList=new LinkedList<Work>();
        totalPage=workService.getOwnWorkByPage(body.getPageNum(),body.getPageSize(),student,workList);
        return getWorkListView(body, totalPage, workList);

    }


    @PostMapping(path = "/ViewAllWork")
    public @ResponseBody
    Object ViewAllWork(@RequestBody PageRequest body) {
        Integer totalPage=-1;
        List<Work> workList=new LinkedList<Work>();
        totalPage=workService.getAllWorkByPage(body.getPageNum(),body.getPageSize(),workList);
        return getWorkListView(body, totalPage, workList);

    }

    private WorkListView getWorkListView(PageRequest body, Integer totalPage, List<Work> workList) {
        List<WorkStatus> workStatusList=new LinkedList<WorkStatus>();
        for(Work work:workList){
            WorkStatus workStatus=new WorkStatus();
            workStatus.setWork(work);
            workStatusList.add(workStatus);
        }
        WorkListView workListView=new WorkListView();
        workListView.setTotalPage(totalPage);
        workListView.setWorkList(workStatusList);
        workListView.setPageNum(body.getPageNum());
        return workListView;
    }

    private WorkStatus getWorkStatus(Work work){
        WorkStatus workStatus=new WorkStatus();
        if(work ==null){
            return workStatus;
        }
        workStatus.setWork(work);
        return workStatus;

    }
}
