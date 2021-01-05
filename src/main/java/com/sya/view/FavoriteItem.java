package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.Favorite;
import lombok.Data;

@Data
public class FavoriteItem {

    @JsonProperty("favorite_id")
    private Integer favoriteId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("favorite_name")
    private String favoriteName;

    @JsonProperty("work_num")
    private Integer workNum;

    public FavoriteItem(Favorite favorite) {
        setFavoriteId(favorite.getId());
        setUserId(favorite.getUser().getId());
        setFavoriteName(favorite.getName());
        setWorkNum(favorite.getWorkNum());
    }
}
