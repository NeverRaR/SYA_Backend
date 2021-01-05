package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateWorkRequest {
    private Integer workId;
    private String workName;
    private String cover;
    private String workDescription;
    private String address;
    private Integer salary;
    private String startDay;
    private String endDay;
    private String startTime;
    private String endTime;
    private Integer weekDay;

    public String getWorkName() {
        return workName;
    }

    @JsonProperty("work_name")
    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    @JsonProperty("work_description")
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

    public String getStartDay() {
        return startDay;
    }

    @JsonProperty("start_day")
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    @JsonProperty("end_day")
    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(String startTime) {
        try {
            this.startTime = formatTime(startTime);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("end_time")
    public void setEndTime(String endTime) {
        try {
            this.endTime = formatTime(endTime);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    @JsonProperty("week_day")
    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    private String formatTime(String time) throws Exception{
        String[] times = time.split(":");
        if (times.length == 0 || times.length > 3){
            throw new Exception(time+":Time length is wrong!");
        }
        int i;
        String formattedTime=times[0];
        for (i = 1; i < times.length; ++i) {
            formattedTime+= ":"+times[i];
        }
        for(;i<3;++i){
            formattedTime+=":00";
        }
        return formattedTime;
    }

    public Integer getWorkId() {
        return workId;
    }

    @JsonProperty("work_id")
    public void setWorkId(Integer workId) {
        this.workId = workId;
    }
}
