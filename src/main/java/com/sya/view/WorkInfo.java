package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.Work;
import lombok.Data;

@Data
public class WorkInfo {

    @JsonProperty("work_id")
    private Integer workId;

    @JsonProperty("work_name")
    private String workName;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("work_description")
    private String workDescription;

    @JsonProperty("address")
    private String address;

    @JsonProperty("salary")
    private Integer salary;

    @JsonProperty("likes_num")
    private Integer likesNum;

    @JsonProperty("collect_num")
    private Integer collectNum;

    public WorkInfo(Work work) {
        setWorkId(work.getId());
        setWorkName(work.getName());
        setCover(work.getCover());
        setWorkDescription(work.getDescription());
        setAddress(work.getAddress());
        setSalary(work.getSalary());
        setLikesNum(work.getLikesNum());
        setCollectNum(work.getCollectNum());
    }
}
