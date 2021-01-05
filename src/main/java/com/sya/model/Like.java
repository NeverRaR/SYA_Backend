package com.sya.model;

import com.sya.model.pk.AnnouncementSendPK;
import com.sya.model.pk.LikePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="work_like")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like implements Serializable {

    private static final long serialVersionUID = -757219868928322811L;

    @EmbeddedId
    private LikePK id = new LikePK();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @MapsId("workId")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @MapsId("studentId")
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

    public LikePK getId() {
        return id;
    }

    public void setId(LikePK id) {
        this.id = id;
    }
}

