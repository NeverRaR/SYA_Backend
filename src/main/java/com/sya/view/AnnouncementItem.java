package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.Announcement;
import com.sya.model.AnnouncementSend;
import lombok.Data;

@Data
public class AnnouncementItem {

    @JsonProperty("announcement_id")
    private Integer announcementId;

    @JsonProperty("user_id")
    private Integer userId;

    private String title;

    private Integer status;

    @JsonProperty("send_time")
    private String sendTime;

    private String content;

    public AnnouncementItem(AnnouncementSend announcementSend) {

        Announcement announcement = announcementSend.getAnnouncement();

        setAnnouncementId(announcement.getId());
        setUserId(announcementSend.getReceiver().getId());
        setTitle(announcement.getTitle());
        setStatus(announcementSend.getStatus());
        setSendTime(announcement.getSendTime().toString());
        setContent(announcement.getContent());
    }
}
