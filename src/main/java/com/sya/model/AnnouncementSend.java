package com.sya.model;

import com.sya.model.pk.AnnouncementSendPK;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Entity
@Table(name ="announcement_send")
public class AnnouncementSend implements Serializable {

    private static final long serialVersionUID = -8855857378053143716L;

    @EmbeddedId
    private AnnouncementSendPK id = new AnnouncementSendPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="announcement_id")
    @MapsId("announcementId")
    private Announcement announcement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    @MapsId("userId")
    private User receiver;

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof AnnouncementSend)) return false;
        AnnouncementSend o=(AnnouncementSend) obj;
        return announcement.getId().equals(o.getAnnouncement().getId())
                && receiver.getId().equals(o.getReceiver().getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private Integer status;

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
