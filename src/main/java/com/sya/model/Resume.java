package com.sya.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Resume {
    @Id
    @Column(name="resume_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer age;

    private String name;

    private String city;

    private String education;

    private String community;

    private String project;

    private String academic;

    private String skill;

    private String introduction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id")
    private User student;

    @OneToMany(mappedBy = "resume",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Apply> applies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Set<Apply> getApplies() {
        return applies;
    }

    public void setApplies(Set<Apply> applies) {
        this.applies = applies;
    }
}
