package com.sya.dao;

import com.sya.model.MessageLibrary;
import com.sya.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDAO extends CrudRepository<MessageLibrary,Integer> {

    @Modifying
    @Query(
            value = "delete from message_library ml where ml.message_id = :messageId",
            nativeQuery = true
    )
    void deleteMessage(@Param("messageId") Integer messageId);

    MessageLibrary findMessageLibraryById(Integer Id);

    Integer countMessageLibrariesBySender(User user);

    List<MessageLibrary> findAllBySender(User user);

    Integer countMessageLibrariesByReceiver(User user);

    List<MessageLibrary> findAllByReceiver(User user);

}
