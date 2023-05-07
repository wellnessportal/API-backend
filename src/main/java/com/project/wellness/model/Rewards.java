package com.project.wellness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Rewards")
public class Rewards {
    @Id
    @Field("_id")
    private String reward;

    public Rewards(String reward){
        this.reward = reward;
    }

    public Rewards(){}

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
