package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResumeIdRequest {
    @JsonProperty("resume_id")
    private Integer resumeId;
}
