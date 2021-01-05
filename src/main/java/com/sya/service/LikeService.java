package com.sya.service;

import com.sya.dao.LikeDAO;
import com.sya.model.Like;
import com.sya.model.pk.LikePK;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    LikeDAO likeDAO;

    public Integer getLike(Integer studentId,Integer workId){
        LikePK likeId=new LikePK(studentId,workId);
        Optional<Like> optionalLike=likeDAO.findById(likeId);
        if(!optionalLike.isPresent()) {
            return 0;
        }
        return 1;
    }

    @Transactional
    public Integer changeLike(Integer studentId,Integer workId){
        LikePK likeId=new LikePK(studentId,workId);
        Optional<Like> optionalLike=likeDAO.findById(likeId);
        if(!optionalLike.isPresent()) {
            Like like=new Like();
            like.setId(likeId);
            likeDAO.save(like);
            return 1;
        }
        likeDAO.delete(optionalLike.get());
        return 0;
    }
}
