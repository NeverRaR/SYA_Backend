package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FavoriteWorkDto {

    @JsonProperty("favorite_id")
    private Integer favoriteId;

    @JsonProperty("work_id")
    private Integer workId;
}
