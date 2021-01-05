package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.Resume;
import lombok.Data;

@Data
public class ResumeView {
    @JsonProperty("student_id")
    private Integer studentId;
    private Integer age;
    @JsonProperty("student_name")
    private String studentName;
    private String city;
    private String education;
    private String community;
    private String project;
    private String academic;
    private String skill;
    private String introduction;

    public void setResume(Resume resume){
        setStudentId(resume.getStudent().getId());
        setAcademic(resume.getAcademic());
        setAge(resume.getAge());
        setStudentName(resume.getName());
        setCity(resume.getCity());
        setEducation(resume.getEducation());
        setCommunity(resume.getCommunity());
        setProject(resume.getProject());
        setAcademic(resume.getAcademic());
        setSkill(resume.getSkill());
        setIntroduction(resume.getIntroduction());
    }
}
