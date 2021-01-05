package com.sya.service;

import com.sya.common.Pagination;
import com.sya.dao.FavoriteDAO;
import com.sya.model.Favorite;
import com.sya.model.User;
import com.sya.request.CreateFavoriteDto;
import com.sya.view.FavoriteItemsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteDAO favoriteDAO;

    public Integer CreateFavorite(CreateFavoriteDto createFavoriteDto, User user) {
        Favorite favorite = new Favorite();
        favorite.setName(createFavoriteDto.getFavoriteName());
        favorite.setUser(user);
        favoriteDAO.save(favorite);
        return favorite.getId();
    }

    public FavoriteItemsPage getFavorites(Pagination pagination, User user) {
//        return new FavoriteItemsPage(favoriteDAO
//                .findByUserId(user.getId(), PageRequest.of(pagination.getPageNum(), pagination.getPageSize()))
//        );
        System.out.println("fuck");
        System.out.println(user);
        Favorite favorite = favoriteDAO
                .findByName("fuck");
        return null;
    }
}
