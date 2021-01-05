package com.sya.controller;

import com.sya.common.Pagination;
import com.sya.model.User;
import com.sya.request.FavoriteWorkDto;
import com.sya.request.CreateFavoriteDto;
import com.sya.request.FavoriteIdDto;
import com.sya.request.UpdateFavoriteDto;
import com.sya.service.AuthenticationService;
import com.sya.service.FavoriteService;
import com.sya.view.FavoriteItemsPage;
import com.sya.view.WorkItemsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final AuthenticationService authenticationService;

    private final FavoriteService favoriteService;

    @PostMapping("CreateFavorite")
    @ResponseBody
    public Integer CreateFavorite(
            @RequestBody CreateFavoriteDto createFavoriteDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        return favoriteService.CreateFavorite(createFavoriteDto, user);
    }

    @PostMapping("GetFavorite")
    @ResponseBody
    public FavoriteItemsPage getFavorites(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
            ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        return favoriteService.getFavorites(pagination, user);
    }

    @PutMapping("UpdateFavorite")
    @ResponseBody
    public UpdateFavoriteDto updateFavorite(
            @RequestBody UpdateFavoriteDto updateFavoriteDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        return favoriteService.updateFavorite(updateFavoriteDto, user);
    }

    @PostMapping("DeleteFavorite")
    @ResponseBody
    public Integer deleteFavorite(
            @RequestBody FavoriteIdDto favoriteIdDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return 0;
        }

        return favoriteService.deleteFavorite(favoriteIdDto, user);
    }

    @PostMapping("GetFavoriteInfo")
    @ResponseBody
    public WorkItemsPage getFavoriteInfo(
            @RequestBody FavoriteIdDto favoriteIdDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        return favoriteService.getFavoriteInfo(favoriteIdDto, user);
    }

    @PostMapping("AddFavoriteWork")
    @ResponseBody
    public Integer addFavoriteWork(
            @RequestBody FavoriteWorkDto favoriteWorkDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return -1;
        }

        return favoriteService.addFavoriteWork(favoriteWorkDto, user);
    }

    @PostMapping("DeleteFavoriteWork")
    @ResponseBody
    public Integer deleteFavoriteWork(
            @RequestBody FavoriteWorkDto favoriteWorkDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return -1;
        }

        return favoriteService.deleteFavoriteWork(favoriteWorkDto, user);
    }
}
