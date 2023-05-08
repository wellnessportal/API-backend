package com.project.wellness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "waitingList")
public class WaitingList {
    @Id
    @Field("_id")
    private int position;
    private String email_id;
    private int event_id;

    public WaitingList(int position, String email_id, int event_id) {
        this.position = position;
        this.email_id = email_id;
        this.event_id = event_id;
    }

    public WaitingList() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
}
