package com.sya.dao;

import com.sya.model.Announcement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementDAO extends CrudRepository<Announcement,Integer> {

//    @Modifying
//    @Query(
//            value = "insert into announcement_send(receiver_id, announcement_id, status) values(:receiverId, :announcementId, :status)",
//            nativeQuery = true
//    )
//    void saveAnnouncementSend(
//            @Param("receiverId") Integer receiverId,
//            @Param("announcementId") Integer announcementId,
//            @Param("status") Integer status
//    );
}
