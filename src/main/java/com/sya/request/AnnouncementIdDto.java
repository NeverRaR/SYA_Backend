package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnnouncementIdDto {

    @JsonProperty("announcement_id")
    private Integer announcementId;
}
