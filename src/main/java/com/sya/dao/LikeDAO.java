package com.sya.dao;

import com.sya.model.Like;
import com.sya.model.User;
import com.sya.model.pk.LikePK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeDAO extends CrudRepository<Like, LikePK> {


}
