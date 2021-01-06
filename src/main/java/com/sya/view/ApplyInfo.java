package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.Apply;
import lombok.Data;

@Data
public class ApplyInfo {

    @JsonProperty("apply_id")
    private Integer applyId;

    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("teacher_name")
    private  String teacherName;

    @JsonProperty("work_name")
    private String workName;

    @JsonProperty("resume_id")
    private Integer resumeId;

    @JsonProperty("status")
    private Integer status;

    public ApplyInfo(Apply apply){
        setApplyId(apply.getId());
        setResumeId(apply.getResume().getId());
        setStatus(apply.getStatus());
        setStudentName(apply.getStudent().getUsername());
        setTeacherName(apply.getTeacher().getUsername());
        setWorkName(apply.getWork().getName());
    }
}
