package com.project.wellness.service;

import com.project.wellness.model.MyEvents;
import com.project.wellness.repository.MyEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyEventsService {
    private MyEventsRepository myEventsRepository;

    @Autowired
    public MyEventsService(MyEventsRepository myEventsRepository) {
        this.myEventsRepository = myEventsRepository;
    }

    public List<MyEvents> findAll() {
        return  myEventsRepository.findAll();
    }
    public void addNewMyEvent(MyEvents myEvents) {
        myEventsRepository.save(myEvents);
    }

    public boolean isBooked(String id, int eventid) {
        List<MyEvents> allMyEvents = myEventsRepository.findAll();
        for(MyEvents myEvents: allMyEvents) {
            if(id.equals(myEvents.getMyEvents_id().getEmail_id()) && eventid == myEvents.getMyEvents_id().getEvent_id()) {
                return true;
            }
        }
        return false;
    }
}
