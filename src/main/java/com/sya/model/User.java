package com.sya.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="user_role")
    private Integer role;

    private Integer gender;

    @Column(name="username")
    private String username;

    private String avatar;

    private String email;

    private String tel;

    private String bank;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registered")
    private Date registered;

    private String password;

    private String salt;

    private String sale;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Announcement> sendAnnouncements;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Announcement> receiveAnnouncements;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<LeaveInformation> leaveInformationSet;

    @OneToMany(mappedBy = "receiver",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<MessageLibrary> receiverMessages;

    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<MessageLibrary> senderMessages;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Work> teacherWorks;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Takes> takesSet;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Salary> salaries;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Apply> studentApplies;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Apply> teacherApplies;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Like> likeSet;

    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Resume studentResume;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public Set<MessageLibrary> getReceiverMessages() {
        return receiverMessages;
    }

    public void setReceiverMessages(Set<MessageLibrary> receiverMessages) {
        this.receiverMessages = receiverMessages;
    }

    public Set<MessageLibrary> getSenderMessages() {
        return senderMessages;
    }

    public void setSenderMessages(Set<MessageLibrary> senderMessages) {
        this.senderMessages = senderMessages;
    }

    public Resume getStudentResume() {
        return studentResume;
    }

    public void setStudentResume(Resume resume) {
        this.studentResume = resume;
    }

    public Set<Work> getTeacherWorks() {
        return teacherWorks;
    }

    public void setTeacherWorks(Set<Work> teacherWorks) {
        this.teacherWorks = teacherWorks;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Set<LeaveInformation> getLeaveInformationSet() {
        return leaveInformationSet;
    }

    public void setLeaveInformationSet(Set<LeaveInformation> leaveInformationSet) {
        this.leaveInformationSet = leaveInformationSet;
    }

    public Set<Announcement> getSendAnnouncements() {
        return sendAnnouncements;
    }

    public void setSendAnnouncements(Set<Announcement> sendAnnouncements) {
        this.sendAnnouncements = sendAnnouncements;
    }

    public Set<Announcement> getReceiveAnnouncements() {
        return receiveAnnouncements;
    }

    public void setReceiveAnnouncements(Set<Announcement> receiveAnnouncements) {
        this.receiveAnnouncements = receiveAnnouncements;
    }

    public Set<Takes> getTakesSet() {
        return takesSet;
    }

    public void setTakesSet(Set<Takes> takesSet) {
        this.takesSet = takesSet;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }

    public Set<Apply> getStudentApplies() {
        return studentApplies;
    }

    public void setStudentApplies(Set<Apply> studentApplies) {
        this.studentApplies = studentApplies;
    }

    public Set<Apply> getTeacherApplies() {
        return teacherApplies;
    }

    public void setTeacherApplies(Set<Apply> teacherApplies) {
        this.teacherApplies = teacherApplies;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<Like> getLikeSet() {
        return likeSet;
    }

    public void setLikeSet(Set<Like> likeSet) {
        this.likeSet = likeSet;
    }
}
