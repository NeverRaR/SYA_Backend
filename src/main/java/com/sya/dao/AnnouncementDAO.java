package com.sya.dao;

import com.sya.model.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementDAO extends CrudRepository<Announcement,Integer> {

}
