package com.sya.controller;

import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.*;
import com.sya.service.*;
import com.sya.view.ErrorView;
import com.sya.view.Message;
import com.sya.view.WorkListView;
import com.sya.view.WorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.acl.Owner;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Work")
public class WorkController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    WorkService workService;

    @Autowired
    LikeService likeService;

    @Autowired
    TakesService takesService;

    @Autowired
    MessageService messageService;

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

    @PostMapping(path = "/ViewWorkInfo")
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
        return getWorkListView(body.getPageNum(), totalPage, workList);

    }


    @PostMapping(path = "/ViewHistoryWork")
    public @ResponseBody
    Object ViewHistoryWork(@RequestBody PageRequest body, @CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId) {
        User teacher=authenticationService.getUser(sessionId);
        if(teacher==null) {
            return new Message("sessionId is invalid!");
        }
        if(teacher.getRole().equals(1)){
            return new Message("ViewHistoryWork is not for students. Students please try ViewOwnWork.");
        }
        Integer totalPage=-1;
        List<Work> workList=new LinkedList<Work>();
        totalPage=workService.getHistoryWorkByPage(body.getPageNum(),body.getPageSize(),teacher,workList);
        return getWorkListView(body.getPageNum(), totalPage, workList);

    }


    @PostMapping(path = "/ViewAllWork")
    public @ResponseBody
    Object ViewAllWork(@RequestBody PageRequest body) {
        Integer totalPage=-1;
        List<Work> workList=new LinkedList<Work>();
        totalPage=workService.getAllWorkByPage(body.getPageNum(),body.getPageSize(),workList);
        return getWorkListView(body.getPageNum(), totalPage, workList);

    }

    @PostMapping(path = "/FindWork")
    public @ResponseBody
    Object FindWork(@RequestBody PageQueryRequest body) {

        Integer totalPage=-1;
        List<Work> workList=new LinkedList<Work>();
        totalPage=workService.findAllWorkByPage(body.getPageNum(),body.getPageSize(),workList, body.getQuery());
        return getWorkListView(body.getPageNum(), totalPage, workList);

    }

    @PostMapping(path = "/FindOwnWork")
    public @ResponseBody
    Object FindOwnWork(@RequestBody PageQueryRequest body,@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId) {
        User student=authenticationService.getUser(sessionId);
        if(student==null) {
            return new Message("sessionId is invalid!");
        }
        Integer totalPage=-1;
        List<Work> workList=new LinkedList<Work>();
        totalPage=workService.findOwnWorkByPage(body.getPageNum(),body.getPageSize(),student,workList, body.getQuery());
        return getWorkListView(body.getPageNum(), totalPage, workList);

    }

    @PostMapping(path = "/ChangeLike")
    public @ResponseBody
    Object ChangeLike(@RequestBody WorkIdRequest body,@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId) {
        User student=authenticationService.getUser(sessionId);
        if(student==null) {
            return new Message("sessionId is invalid!");
        }

        Work work=workService.getWorkById(body.getWorkId());
        if(work==null){
            return new Message("Work is not exist!");
        }
        return likeService.changeLike(student, work);
    }

    @PostMapping(path = "/GetLike")
    public @ResponseBody
    Object GetLike(@RequestBody WorkIdRequest body, @CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId) {
        User student=authenticationService.getUser(sessionId);
        if(student==null) {
            return new Message("sessionId is invalid!");
        }
        return likeService.getLike(student.getId(), body.getWorkId());
    }

    @PostMapping(path = "/Getresign")
    public @ResponseBody
    Object resign(@RequestBody WorkIdRequest body,@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User student=authenticationService.getUser(sessionId);
        if(student==null) {
            return new Message("sessionId is invalid!");
        }
        Integer result = takesService.resign(student.getId(), body.getWorkId());
        //如果辞职成功，那么将向老师发送一条辞职信息
        if(result.equals(1)) messageService.CreateResignMessage(student,body.getWorkId());
        return result;
    }

    @PostMapping(path = "/Deleteresign")
    public @ResponseBody
    Object deleteResign(@RequestBody WorkIdRequest body,@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User student=authenticationService.getUser(sessionId);
        if(student==null) {
            return new Message("sessionId is invalid!");
        }
        return takesService.deleteResign(student.getId(), body.getWorkId());
    }

    @PostMapping(path = "/ChangeWorkInfo")
    public @ResponseBody
    Object ChangeWorkInfo(@RequestBody UpdateWorkRequest body, @CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User teacher=authenticationService.getUser(sessionId);
        if(teacher.getRole().equals(1)) {
            return  new Message("You are a student!");
        }
        User owner=workService.getWorkOwner(body.getWorkId());
        if(owner ==null){
            return new ErrorView(-1,"Work isn't exist!");
        }
        if(!owner.getId().equals(teacher.getId())){
            return new Message("You are not the owner!");
        }
        Work work=workService.updateWork(body);
        return getWorkStatus(work);

    }

    private WorkListView getWorkListView(Integer pageNum, Integer totalPage, List<Work> workList) {
        List<WorkStatus> workStatusList=new LinkedList<WorkStatus>();
        for(Work work:workList){
            WorkStatus workStatus=new WorkStatus();
            workStatus.setWork(work);
            workStatusList.add(workStatus);
        }
        WorkListView workListView=new WorkListView();
        workListView.setTotalPage(totalPage);
        workListView.setWorkList(workStatusList);
        workListView.setPageNum(pageNum);
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
