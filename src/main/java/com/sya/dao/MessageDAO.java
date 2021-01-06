package com.sya.dao;

import com.sya.model.MessageLibrary;
import com.sya.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageDAO extends CrudRepository<MessageLibrary,Integer> {

    Page<MessageLibrary> findAllBySender(User user, Pageable pageable);

    Page<MessageLibrary> findAllBySenderAndContentType(User user, Integer contentType, Pageable pageable);

    Page<MessageLibrary> findAllByReceiverAndStatusNotAndContentType(User receiver, Integer status, Integer contentType, Pageable pageable);

    MessageLibrary findMessageLibraryById(Integer Id);


}
