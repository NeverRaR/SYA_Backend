package com.sya.controller;

import com.sya.common.Pagination;
import com.sya.model.User;
import com.sya.request.CreateFavoriteDto;
import com.sya.service.AuthenticationService;
import com.sya.service.FavoriteService;
import com.sya.view.FavoriteItemsPage;
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
        System.out.println("shit");
        if (user == null) {
            return null;
        }

        return favoriteService.getFavorites(pagination, user);
    }
}
