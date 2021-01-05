package com.sya.dao;

import com.sya.model.Favorite;
import com.sya.model.User;
import com.sya.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface FavoriteDAO extends CrudRepository<Favorite, Integer> {
    Page<Favorite> findAllByUserId(Integer userId, Pageable pageable);

    @Modifying
    @Query(
            value = "insert into favorite_has_work(work_id, favorite_id, add_time) value(:workId, :favoriteId, :date)",
            nativeQuery = true
    )
    void insertFavoriteHasWork(@Param("workId") Integer workId, @Param("favoriteId") Integer favoriteId, @Param("date") Date date);

    @Modifying
    @Query(
            value = "delete from favorite_has_work fhw where fhw.work_id = :workId and fhw.favorite_id = :favoriteId",
            nativeQuery = true
    )
    void deleteFavoriteHasWork(@Param("workId") Integer workId, Integer favoriteId);
}
