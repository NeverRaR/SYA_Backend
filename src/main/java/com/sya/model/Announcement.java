package com.sya.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Announcement {

    @Id
    @Column(name="announcement_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String title;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="send_time")
    private Date sendTime;

    @OneToMany(mappedBy = "announcement",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<AnnouncementSend> announcementSends;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Set<AnnouncementSend> getAnnouncementSends() {
        return announcementSends;
    }

    public void setAnnouncementSends(Set<AnnouncementSend> announcementSends) {
        this.announcementSends = announcementSends;
    }
}
