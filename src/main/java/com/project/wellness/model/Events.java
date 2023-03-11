package com.project.wellness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "EVENTS")
public class Events implements Comparable<Events>{

    @Transient
    public static final String SEQUENCE_NAME = "events_sequence";

    @Id
    @Field("_id")
    private int event_id;
    private String type;
    private String name;
    private LocalTime time;
    private LocalDate date;
    private String zLink;
    private int capacity;
    private String instructor;
    private String desc;
    private String image_link;

    public Events(String type, String name, LocalTime time, LocalDate date, String zLink,
                  int capacity, String instructor, String desc, String image_link) {
        this.type = type;
        this.name = name;
        this.time = time;
        this.date = date;
        this.zLink = zLink;
        this.capacity = capacity;
        this.instructor = instructor;
        this.desc = desc;
        this.image_link = image_link;
    }

    public int getId() { return event_id;}

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getzLink() {
        return zLink;
    }

    public void setzLink(String zLink) {
        this.zLink = zLink;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    @Override
    public int compareTo(Events events) {
        return getDate().compareTo(events.getDate());
    }

}

