package com.sya.service;

import com.sya.common.Pagination;
import com.sya.dao.AnnouncementDAO;
import com.sya.dao.AnnouncementSendDAO;
import com.sya.dao.UserDAO;
import com.sya.model.Announcement;
import com.sya.model.AnnouncementSend;
import com.sya.model.User;
import com.sya.request.AnnouncementIdDto;
import com.sya.request.CreateAnnouncementDto;
import com.sya.view.AnnouncementItem;
import com.sya.view.AnnouncementItemsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementDAO announcementDAO;

    private final AnnouncementSendDAO announcementSendDAO;

    private final UserDAO userDAO;

    @Transactional
    public Integer createAnnouncement(CreateAnnouncementDto createAnnouncementDto, User user) {
        // 找到所有学生账号，role为1的是学生
        List<User> userList = userDAO.findAllByRole(1);

        System.out.println(userList);

        Announcement announcement = new Announcement();
        announcement.setUser(user);
        announcement.setTitle(createAnnouncementDto.getTitle());
        announcement.setContent(createAnnouncementDto.getContent());
        announcement.setSendTime(new Date());
        announcement.setAnnouncementSends(new HashSet<>());

        announcementDAO.save(announcement);

        System.out.println(announcement.getId());
        userList.forEach(currUser -> {
            AnnouncementSend announcementSend = new AnnouncementSend();
            announcementSend.setAnnouncement(announcement);
            announcementSend.setReceiver(currUser);
            announcementSend.setStatus(0);
            announcement.getAnnouncementSends().add(announcementSend);
        });

        announcementDAO.save(announcement);

        return announcement.getId();
    }

    @Transactional
    public AnnouncementItemsPage getAnnouncement(Pagination pagination, User user) {
        Page<AnnouncementSend> announcementSendPage = announcementSendDAO.findAllByReceiverId(
                user.getId(),
                PageRequest.of(pagination.getPageNum() - 1, pagination.getPageSize())
        );

        return new AnnouncementItemsPage(announcementSendPage);
    }

    @Transactional
    public AnnouncementItem getAnnouncementContent(AnnouncementIdDto announcementIdDto, User user) {
        AnnouncementSend announcementSend = announcementSendDAO.findByReceiverIdAndAndAnnouncementId(
                user.getId(),
                announcementIdDto.getAnnouncementId()
        );

        if (announcementSend == null) {
            return null;
        }

        AnnouncementItem resItem = new AnnouncementItem(announcementSend);

        announcementSend.setStatus(1);
        announcementSendDAO.save(announcementSend);

        return resItem;
    }

    @Transactional
    public Integer deleteAnnouncement(AnnouncementIdDto announcementIdDto, User user) {
        AnnouncementSend announcementSend = announcementSendDAO.findByReceiverIdAndAndAnnouncementId(
                user.getId(),
                announcementIdDto.getAnnouncementId()
        );

        if (announcementSend == null) {
            return 0;
        }

        announcementSendDAO.delete(announcementSend);
        return 2;
    }

    @Transactional
    public Integer deleteAllAnnouncement(AnnouncementIdDto announcementIdDto, User user) {
        Optional<Announcement> optionalAnnouncement = announcementDAO.findById(announcementIdDto.getAnnouncementId());

        if (!optionalAnnouncement.isPresent()) {
            return 0;
        }

        announcementDAO.delete(optionalAnnouncement.get());
        return 2;
    }

    public AnnouncementItemsPage getSendAnnouncement(Pagination pagination, User user) {
        Page<Announcement> announcementPage = announcementDAO.findAllByUserId(
                user.getId(),
                PageRequest.of(pagination.getPageNum() - 1, pagination.getPageSize())
        );

        AnnouncementItemsPage announcementItemsPage = new AnnouncementItemsPage();
        announcementItemsPage.setAnnouncement(announcementPage);
        return announcementItemsPage;
    }
}
