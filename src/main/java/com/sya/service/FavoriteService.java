package com.sya.service;

import com.sya.common.Pagination;
import com.sya.dao.FavoriteDAO;
import com.sya.dao.WorkDAO;
import com.sya.model.Favorite;
import com.sya.model.User;
import com.sya.model.Work;
import com.sya.request.FavoriteWorkDto;
import com.sya.request.CreateFavoriteDto;
import com.sya.request.FavoriteIdDto;
import com.sya.request.UpdateFavoriteDto;
import com.sya.view.FavoriteItemsPage;
import com.sya.view.WorkItemsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteDAO favoriteDAO;

    private final WorkDAO workDAO;

    @Transactional
    public Integer CreateFavorite(CreateFavoriteDto createFavoriteDto, User user) {
        Favorite favorite = new Favorite();
        favorite.setName(createFavoriteDto.getFavoriteName());
        favorite.setUser(user);
        favorite.setWorkNum(0);
        favoriteDAO.save(favorite);
        return favorite.getId();
    }

    public FavoriteItemsPage getFavorites(Pagination pagination, User user) {
        return new FavoriteItemsPage(favoriteDAO
                .findAllByUserId(user.getId(), PageRequest.of(pagination.getPageNum() - 1, pagination.getPageSize()))
        );
    }

    @Transactional
    public UpdateFavoriteDto updateFavorite(UpdateFavoriteDto updateFavoriteDto, User user) {
        Favorite favorite = checkFavorite(updateFavoriteDto.getFavoriteId(), user);

        if (favorite == null) {
            return null;
        }

        favorite.setName(updateFavoriteDto.getFavoriteName());
        favoriteDAO.save(favorite);
        return new UpdateFavoriteDto(favorite.getId(), favorite.getName(), favorite.getWorkNum());
    }

    @Transactional
    public Integer deleteFavorite(FavoriteIdDto favoriteIdDto, User user) {
        Favorite favorite = checkFavorite(favoriteIdDto.getFavoriteId(), user);

        if (favorite == null) {
            return 0;
        }

        favoriteDAO.delete(favorite);
        return 1;
    }

    public WorkItemsPage getFavoriteInfo(FavoriteIdDto favoriteIdDto, User user) {
        Favorite favorite = checkFavorite(favoriteIdDto.getFavoriteId(), user);

        if (favorite == null) {
            return null;
        }

        List<Work> workList = workDAO.findAllByFavoriteHasWorks_Favorite(favorite);

        WorkItemsPage workItemsPage = new WorkItemsPage();
        workItemsPage.setWorkList(workList);

        return workItemsPage;
    }

    @Transactional
    public Integer addFavoriteWork(FavoriteWorkDto favoriteWorkDto, User user) {
        Favorite favorite = checkFavorite(favoriteWorkDto.getFavoriteId(), user);

        if (favorite == null) {
            return -1;
        }

        Work work = checkWork(favoriteWorkDto.getWorkId());

        if (work == null) {
            return -1;
        }

        favoriteDAO.insertFavoriteHasWork(favoriteWorkDto.getWorkId(), favoriteWorkDto.getFavoriteId(), new Date());
        return 1;
    }

    @Transactional
    public Integer deleteFavoriteWork(FavoriteWorkDto favoriteWorkDto, User user) {
        Favorite favorite = checkFavorite(favoriteWorkDto.getFavoriteId(), user);

        if (favorite == null) {
            return -1;
        }

        Work work = checkWork(favoriteWorkDto.getWorkId());

        if (work == null) {
            return -1;
        }

        favoriteDAO.deleteFavoriteHasWork(favoriteWorkDto.getWorkId(), favoriteWorkDto.getFavoriteId());
        return 1;
    }

    private Favorite checkFavorite(Integer favoriteId, User user) {
        Optional<Favorite> optionalFavorite = favoriteDAO.findById(favoriteId);

        if (!optionalFavorite.isPresent()) {
            return null;
        }

        Favorite favorite = optionalFavorite.get();

        if (!favorite.getUser().getId().equals(user.getId())) {
            return null;
        }

        return favorite;
    }

    private Work checkWork(Integer workId) {
        Optional<Work> optionalWork = workDAO.findById(workId);
        return optionalWork.orElse(null);
    }
}
