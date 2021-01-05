package com.sya.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="work_like")
@Entity
public class Like implements Serializable {

    private static final long serialVersionUID = -757219868928322811L;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User student;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof com.sya.model.Takes)) return false;
        com.sya.model.Takes o = (com.sya.model.Takes) obj;
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
}

