package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveManagementRequest {

    @JsonProperty("leave_id")
    private Integer leaveId;

    @JsonProperty("status")
    private Integer status;
}
