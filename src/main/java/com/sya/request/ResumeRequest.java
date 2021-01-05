package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResumeRequest {
    private Integer age;
    @JsonProperty("student_name")
    private String studentName;
    private String city;
    private String education;
    private String community;
    private String project;
    private String academic;
    private String skill;
    private String introduction;
}
