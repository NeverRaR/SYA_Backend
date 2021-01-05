package com.sya.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Work {

    @Id
    @Column(name="work_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="work_name")
    private String name;

    private String cover;

    @Column(name="work_description")
    private String description;

    private String address;

    private Integer salary;

    @Formula("(select count(*) from takes a where a.work_id = work_id)")
    private Integer likesNum;

    @Formula("(select count(*) from favorite_has_work a where a.work_id = work_id)")
    private Integer collectNum;

    @Column(name="start_day")
    private String startDay;

    @Column(name="end_day")
    private String endDay;

    @Column(name="start_time")
    private String startTime;

    @Column(name="end_time")
    private String endTime;

    @Column(name ="week_day")
    private Integer weekDay;

    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<FavoriteHasWork> favoriteHasWorks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Takes> takesSet;

    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Salary> salaries;

    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Apply> applies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum){
        this.likesNum=likesNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum){
        this.collectNum=collectNum;

    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getTotalTime() {
        double totalHour =0.0;
        int totalDay=0;
        int totalWeek=0;
        try {
            String[] startTimes = startTime.split(":");
            String[] endTimes = endTime.split(":");
            if (startTimes.length != endTimes.length){
                return null;
            }
            if (startTimes.length == 0 || startTimes.length > 3) {
                return null;
            }
            int i;
            double rate = 1.0;
            for (i = 0; i < startTimes.length; ++i) {
                totalHour += (Double.parseDouble(endTimes[i]) - Double.parseDouble(startTimes[i])) * rate;
                rate /= 60;
            }

            String[] startDays = startDay.split("-");
            String[] endDays = endDay.split("-");
            totalDay +=(Integer.parseInt (endDays[0])-Integer.parseInt (startDays[0]))*365;
            totalDay +=(Integer.parseInt (endDays[1])-Integer.parseInt (startDays[1]))*30;
            totalDay +=Integer.parseInt (endDays[2])-Integer.parseInt (startDays[2]);
            totalWeek=1+totalDay/7;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return totalWeek*totalHour;
    }
    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Set<FavoriteHasWork> getFavoriteHasWorks() {
        return favoriteHasWorks;
    }

    public void setFavoriteHasWorks(Set<FavoriteHasWork> favoriteHasWorks) {
        this.favoriteHasWorks = favoriteHasWorks;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Set<Takes> getTakesSet() {
        return takesSet;
    }

    public void setTakesSet(Set<Takes> takesSet) {
        this.takesSet = takesSet;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }

    public Set<Apply> getApplies() {
        return applies;
    }

    public void setApplies(Set<Apply> applies) {
        this.applies = applies;
    }
}
