package com.sya.controller;

import com.sya.model.Resume;
import com.sya.model.User;
import com.sya.request.ResumeIdRequest;
import com.sya.request.ResumeRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.ResumeService;
import com.sya.view.ErrorView;
import com.sya.view.Message;
import com.sya.view.ResumeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/Resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/CreateResume")
    public @ResponseBody
    Object CreateResume (@RequestBody ResumeRequest body, @CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User student=authenticationService.getUser(sessionId);
        if(!student.getRole().equals(1)){
            return  new Message("You aren't student!");
        }
        Resume resume=resumeService.createResume(body,student);
        return getResumeView(resume);
    }

    @DeleteMapping(path = "/DeleteResume")
    public @ResponseBody
    Object DeleteResume (@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User student=authenticationService.getUser(sessionId);
        if(!student.getRole().equals(1)){
            return  new Message("You aren't student!");
        }
        Integer status_code=resumeService.deleteResume(student);
        if(status_code.equals(-1)){
            return new ErrorView(-1,"You dont have resume!");
        }
        return  status_code;
    }

    @PutMapping(path = "/UpdateResume")
    public @ResponseBody
    Object UpdateResume (@RequestBody ResumeRequest body,@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User student=authenticationService.getUser(sessionId);
        if(!student.getRole().equals(1)){
            return  new Message("You aren't student!");
        }
        Resume resume=resumeService.updateResume(body,student);
        if(resume==null) {
            return new ErrorView(-1,"You dont have resume!");
        }
        return  getResumeView(resume);
    }

    @PostMapping(path = "/GetResumeInfo")
    public @ResponseBody
    Object GetResumeInfo (@RequestBody ResumeIdRequest body){
        Resume resume=resumeService.getResume(body.getResumeId());
        if(resume==null) {
            return new ErrorView(-1,"Resume isn't exist!");
        }
        return  getResumeView(resume);
    }

    @PostMapping(path = "/GetResume")
    public @ResponseBody
    Object GetResume (@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User student=authenticationService.getUser(sessionId);
        if(!student.getRole().equals(1)){
            return  new Message("You aren't student!");
        }
        Resume resume=student.getStudentResume();
        if(resume==null){
            return new ErrorView(-1,"You dont have resume!");
        }
        return getResumeView(resume);
    }

    private ResumeView getResumeView(Resume resume){
        ResumeView resumeView=new ResumeView();
        if(resume==null){
            return  resumeView;
        }
        resumeView.setResume(resume);
        return resumeView;
    }
}
