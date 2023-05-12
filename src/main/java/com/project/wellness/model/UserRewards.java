package com.project.wellness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "userRewards")
public class UserRewards {
    @Id
    @Field("_id")
    private int slnum;
    private String email;
    private String reward;

    public UserRewards(int slnum, String email, String reward) {
        this.slnum = slnum;
        this.email = email;
        this.reward = reward;
    }

    public UserRewards() {

    }

    public int getSlnum() {
        return slnum;
    }

    public void setSlnum(int slnum) {
        this.slnum = slnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
