package com.sya.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="favorite_has_work")
public class FavoriteHasWork implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="work_id")
    private Work work;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="favorite_id")
    private Favorite favorite;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="add_time")
    private Date addTime;

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
