package com.sya.model;

import com.sya.model.pk.LikePK;
import com.sya.model.pk.TakesPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Takes implements Serializable {

    private static final long serialVersionUID = -8473363729158033277L;
    @EmbeddedId
    private TakesPK id = new TakesPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @MapsId("workId")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @MapsId("studentId")
    private User student;

    @Column(name ="work_time")
    private Double  workTime;

    @Column(name ="absent_num")
    private Integer absentNum;

    @Column(name = "absent_time")
    private Double absentTime;

    private Integer status;

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Takes)) return false;
        Takes o=(Takes) obj;
        return work.getId().equals(o.getWork().getId())
                && student.getId().equals(o.getStudent().getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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

    public Double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Double workTime) {
        this.workTime = workTime;
    }

    public Integer getAbsentNum() {
        return absentNum;
    }

    public void setAbsentNum(Integer absentNum) {
        this.absentNum = absentNum;
    }

    public Double getAbsentTime() {
        return absentTime;
    }

    public void setAbsentTime(Double absentTime) {
        this.absentTime = absentTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
