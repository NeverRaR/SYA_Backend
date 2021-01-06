package com.sya.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="leave_information")
public class LeaveInformation {

    @Id
    @Column(name="leave_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="work_id")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id")
    private User student;

    private String content;

    private String proof;

    private Integer status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="request_time")
    private Date requestTime;

    @Column(name = "leave_day")
    private String leaveDay;

    @Column(name = "leave_start")
    private String leaveStart;

    @Column(name = "leave_end")
    private String leaveEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public String getLeaveDay() {
        return leaveDay;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public void setLeaveDay(String leaveDay) {
        this.leaveDay = leaveDay;
    }

    public String getLeaveStart() {
        return leaveStart;
    }

    public void setLeaveStart(String leaveStart) {
        this.leaveStart = leaveStart;
    }

    public String getLeaveEnd() {
        return leaveEnd;
    }

    public void setLeaveEnd(String leaveEnd) {
        this.leaveEnd = leaveEnd;
    }

    public Double getDuration(){
        double totalTime = 0.0;
        try {
            String[] startTimes = leaveStart.split(":");
            String[] endTimes = leaveEnd.split(":");
            if (startTimes.length != endTimes.length) return null;
            if (startTimes.length == 0 || startTimes.length > 3) return null;
            int i;
            double rate = 1.0;
            for (i = 0; i < startTimes.length; ++i) {
                totalTime += Double.parseDouble(endTimes[i]) - Double.parseDouble(startTimes[i]) * rate;
                rate /= 60;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return totalTime;
    }
}
