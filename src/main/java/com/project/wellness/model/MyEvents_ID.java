package com.project.wellness.model;

import java.util.Objects;

public class MyEvents_ID {
    private String email_id;
    private int event_id;

    public MyEvents_ID(String email_id, int event_id) {
        this.email_id = email_id;
        this.event_id = event_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEvents_ID that = (MyEvents_ID) o;
        return event_id == that.event_id && Objects.equals(email_id, that.email_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email_id, event_id);
    }

    @Override
    public String toString() {
        return "MyEvents_ID{" +
                "username='" + email_id + '\'' +
                ", event_id=" + event_id +
                '}';
    }
}

