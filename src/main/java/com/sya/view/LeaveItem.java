package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.LeaveInformation;
import lombok.Data;

import java.util.Date;

@Data
public class LeaveItem {

    @JsonProperty("leave_id")
    private Integer leaveId;

    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("work_name")
    private String workName;

    @JsonProperty("student_id")
    private Integer studentId;

    @JsonProperty("work_id")
    private Integer workId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("proof")
    private String proof;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("leave_duration")
    private Double leaveDuration;

    @JsonProperty("request_time")
    private Date requestTime;

    @JsonProperty("leave_day")
    private String leaveDay;

    @JsonProperty("leave_start")
    private String leaveStart;

    @JsonProperty("leave_end")
    private String leaveEnd;

    public LeaveItem(LeaveInformation leaveInformation){
        setLeaveId(leaveInformation.getId());
        setStudentId(leaveInformation.getStudent().getId());
        setStudentName(leaveInformation.getStudent().getUsername());
        setWorkId(leaveInformation.getWork().getId());
        setWorkName(leaveInformation.getWork().getName());
        setContent(leaveInformation.getContent());
        setProof(leaveInformation.getProof());
        setStatus(leaveInformation.getStatus());
        setRequestTime(leaveInformation.getRequestTime());
        setLeaveDay(leaveInformation.getLeaveDay());
        setLeaveStart(leaveInformation.getLeaveStart());
        setLeaveEnd(leaveInformation.getLeaveEnd());
        setLeaveDuration(leaveInformation.getDuration());
    }
}
