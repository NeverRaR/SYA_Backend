package com.sya.controller;

import com.sya.model.Resume;
import com.sya.model.User;
import com.sya.request.CreateWorkRequest;
import com.sya.request.ResumeRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.ResumeService;
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

    private ResumeView getResumeView(Resume resume){
        ResumeView resumeView=new ResumeView();
        if(resume==null){
            return  resumeView;
        }
        resumeView.setResume(resume);
        return resumeView;
    }
}
