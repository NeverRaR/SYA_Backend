package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.Work;
import lombok.Data;

@Data
public class WorkStatus {
    private Integer id;
    private String workName;
    private String cover;
    private String workDescription;
    private String address;
    private Integer salary;
    private Integer likesNum;
    private Integer collectNum;
    private String startDay;
    private String endDay;
    private String startTime;
    private String endTime;
    private Double totalTime;
    private Integer weekDay;
    private Integer status;

    public void setWork(Work work){
        setId(work.getId());
        setAddress(work.getAddress());
        setWorkDescription(work.getDescription());
        setWorkName(work.getName());
        setCollectNum(work.getCollectNum());
        setCover(work.getCover());
        setEndDay(work.getEndDay());
        setEndTime(work.getEndTime());
        setLikesNum(work.getLikesNum());
        setSalary(work.getSalary());
        setStartDay(work.getStartDay());
        setStartTime(work.getStartTime());
        setTotalTime(work.getTotalTime());
        setWeekDay(work.getWeekDay());
    }
    
    @JsonProperty("work_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("work_name")
    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @JsonProperty("work_description")
    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @JsonProperty("likes_num")
    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum) {
        this.likesNum = likesNum;
    }

    @JsonProperty("collect_num")
    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    @JsonProperty("start_day")
    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    @JsonProperty("end_day")
    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    @JsonProperty("start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("total_time")
    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    @JsonProperty("week_day")
    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }
}
