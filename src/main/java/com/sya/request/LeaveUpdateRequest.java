package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveUpdateRequest {

    @JsonProperty("leave_id")
    private Integer leaveId;

    @JsonProperty("work_id")
    private Integer workId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("proof")
    private String proof;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("leave_day")
    private String leaveDay;

    @JsonProperty("leave_start")
    private String leaveStart;

    @JsonProperty("leave_end")
    private String leaveEnd;

    public LeaveUpdateRequest(LeaveRequset leaveRequset){
        setContent(leaveRequset.getContent());
        setLeaveDay(leaveRequset.getLeaveDay());
        setLeaveStart(leaveRequset.getLeaveStart());
        setLeaveEnd(leaveRequset.getLeaveEnd());
        setStatus(leaveRequset.getStatus());
        setProof(leaveRequset.getProof());
    }
}
