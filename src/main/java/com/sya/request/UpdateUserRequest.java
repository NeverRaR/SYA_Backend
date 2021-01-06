package com.sya.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private Integer gender;
    private String avatar;
    private String tel;
    private String bank;

    public void setGender(Boolean gender){
        if(gender) {
            this.gender=1;
        }
        else {
            this.gender=0;
        }
    }
}
