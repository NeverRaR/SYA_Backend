package com.sya.service;

import com.sya.dao.ResumeDAO;
import com.sya.model.Resume;
import com.sya.model.User;
import com.sya.request.ResumeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    ResumeDAO resumeDAO;

    @Transactional
    public Resume createResume(ResumeRequest body, User student){
        Resume resume=new Resume();
        makeResume(resume,body,student);
        resumeDAO.save(resume);
        return  resume;
    }

    @Transactional
    public Integer deleteResume(User student){
        Resume resume=student.getStudentResume();
        if(resume==null) {
            return -1;
        }
        resumeDAO.delete(resume);
        return 1;
    }

    public Resume getResume(Integer resumeId){
        return resumeDAO.findById(resumeId).orElse(null);
    }

    @Transactional
    public Resume updateResume(ResumeRequest body,User student){
        Resume resume=student.getStudentResume();
        if(resume==null) {
            return null;
        }
        makeResume(resume,body,student);
        resumeDAO.save(resume);
        return resume;
    }

    private void makeResume(Resume resume,ResumeRequest body,User student){
        resume.setAcademic(body.getAcademic());
        resume.setAge(body.getAge());
        resume.setName(body.getStudentName());
        resume.setCity(body.getCity());
        resume.setEducation(body.getEducation());
        resume.setCommunity(body.getCommunity());
        resume.setProject(body.getProject());
        resume.setSkill(body.getSkill());
        resume.setIntroduction(body.getIntroduction());
        resume.setStudent(student);
    }
}
