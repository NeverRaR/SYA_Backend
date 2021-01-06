package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.common.Pagination;
import com.sya.model.Announcement;
import com.sya.model.AnnouncementSend;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.text.Annotation;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AnnouncementItemsPage extends Pagination {

    @JsonProperty("announcementItem")
    private List<AnnouncementItem> announcementItems;

    public AnnouncementItemsPage(Page<AnnouncementSend> announcementSendPage) {
        setPageNum(announcementSendPage.getNumber() + 1);
        setPageSize(announcementSendPage.getSize());
        setTotalPage(announcementSendPage.getTotalPages());

        setAnnouncementItems(announcementSendPage.stream().map(AnnouncementItem::new).collect(Collectors.toList()));
    }

    public void setAnnouncement(Page<Announcement> announcementPage) {
        setPageNum(announcementPage.getNumber() + 1);
        setPageSize(announcementPage.getSize());
        setTotalPage(announcementPage.getTotalPages());

        setAnnouncementItems(announcementPage.stream().map(AnnouncementItem::new).collect(Collectors.toList()));
    }
}
