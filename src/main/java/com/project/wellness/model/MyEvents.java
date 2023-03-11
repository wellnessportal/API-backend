package com.project.wellness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "MyEvents")
public class MyEvents {
    @Id
    @Field("_id")
    private MyEvents_ID myEvents_id;

    public MyEvents() {
    }

    public MyEvents(MyEvents_ID myEvents_id) {
        this.myEvents_id = myEvents_id;
    }
    public MyEvents_ID getMyEvents_id() {
        return myEvents_id;
    }
    public void setMyEvents_id(MyEvents_ID myEvents_id) {
        this.myEvents_id = myEvents_id;
    }

    @Override
    public String toString() {
        return myEvents_id.toString();
    }
}

