package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ViewWorkInfoRequest {
    private Integer workId;

    public Integer getWorkId() {
        return workId;
    }

    @JsonProperty("work_id")
    public void setWorkId(Integer workId) {
        this.workId = workId;
    }
}
