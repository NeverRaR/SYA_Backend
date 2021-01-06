package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.User;
import lombok.Data;

@Data
public class UserStatus {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_role")
    private Integer userRole;
    @JsonProperty("user_name")
    private String userName;
    private Boolean gender;
    private String avatar;
    private String email;
    private String tel;
    private String bank;
    @JsonProperty("nof_apply")
    private Integer applyNum;
    @JsonProperty("nof_absent")
    private Integer absentNum;
    @JsonProperty("work_time")
    private Double workTime;
    @JsonProperty("absent_time")
    private Double absentTime;
    @JsonProperty("income")
    private Double income;

    public void setUser(User user){
        setUserId(user.getId());
        setUserRole(user.getRole());
        setUserName(user.getUsername());
        if(user.getGender() != null) {
            setGender(user.getGender().equals(1));
        }
        if(user.getAvatar() != null) {
            setAvatar(user.getAvatar());
        }
        if(user.getEmail()!=null) {
            setEmail(user.getEmail());
        }
        if(user.getTel()!=null) {
            setTel(user.getTel());
        }
        if(user.getBank()!=null) {
            setBank(user.getBank());
        }
    }
}
