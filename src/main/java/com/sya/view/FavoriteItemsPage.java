package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.common.Pagination;
import com.sya.model.Favorite;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class FavoriteItemsPage extends Pagination {

    @JsonProperty("favoriteItem")
    private List<FavoriteItem> favoriteItems;

    public FavoriteItemsPage(Page<Favorite> favoritePage) {
        setPageNum(favoritePage.getNumber());
        setPageSize(favoritePage.getSize());
        setTotalPage(favoritePage.getTotalPages());
        setFavoriteItems(favoritePage);

    }

    private void setFavoriteItems(Page<Favorite> favoritePage) {
        setFavoriteItems(
                favoritePage
                        .stream()
                        .map(FavoriteItem::new)
                        .collect(Collectors.toList())
        );
    }

    public void setFavoriteItems(List<FavoriteItem> favoriteItems) {
        this.favoriteItems = favoriteItems;
    }
}
