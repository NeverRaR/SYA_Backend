package com.sya.dao;

import com.sun.istack.NotNull;
import com.sya.model.AnnouncementSend;
import com.sya.model.pk.AnnouncementSendPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementSendDAO extends CrudRepository<AnnouncementSend, AnnouncementSendPK> {

    Page<AnnouncementSend> findAllByReceiverId(Integer receiverId, Pageable pageable);

    AnnouncementSend findByReceiverIdAndAndAnnouncementId(Integer receiverId, Integer announcementId);

    Page<AnnouncementSend> findAllByAnnouncement_UserId(Integer userId, Pageable pageable);
}

