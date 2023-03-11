package com.project.wellness.service;

import com.project.wellness.model.Events;
import com.project.wellness.model.MyEvents;
import com.project.wellness.model.MyEvents_ID;
import com.project.wellness.repository.EventsRepository;
import com.project.wellness.repository.MyEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomePageService {
    private MyEventsRepository myEventsRepository;
    private EventsRepository eventsRepository;

    @Autowired
    public HomePageService(MyEventsRepository myEventsRepository, EventsRepository eventsRepository) {
        this.myEventsRepository = myEventsRepository;
        this.eventsRepository = eventsRepository;
    }

    public List<Events> listBookedEvents(String username) {
        List<Events> bookedEvents = new ArrayList<Events>();
        List<Integer> eventsIdList = new ArrayList<Integer>();
        List<MyEvents> myEventsList = myEventsRepository.findAll();

        for(MyEvents myEvents: myEventsList) {
            MyEvents_ID myEvents_id = myEvents.getMyEvents_id();
            String userid = myEvents_id.getEmail_id();
            int eventid = myEvents_id.getEvent_id();

            if(userid.equals(username)) {
                eventsIdList.add(eventid);
            }
        }

        for(Integer eventid: eventsIdList) {
            Events event = eventsRepository.findById(eventid).orElse(null);
            if(event != null) {
                bookedEvents.add(event);
            }
        }
        if(bookedEvents.isEmpty()) {
            return null;
        }
        return bookedEvents;
    }
    public String cancelEvent(String username, int eventid) {
        Events event = eventsRepository.findById(eventid).orElse(null);
        if (event == null) {
            return "Event not found";
        }
        int capacity = event.getCapacity();
        event.setCapacity(capacity + 1);
        eventsRepository.save(event);
        List<MyEvents> myEvents = myEventsRepository.findAll();
        if(myEvents.isEmpty()) {
            return "No bookings found";
        }
        for(MyEvents myevent: myEvents) {
            if(myevent.getMyEvents_id().getEvent_id() == eventid &&
                    myevent.getMyEvents_id().getEmail_id().equals(username)) {
                myEventsRepository.delete(myevent);
            }
        }
        return "Successfully cancelled event";
    }


    public List<Events> getUpcomingEvents() {
        List<Events> upcomingEvents = new ArrayList<Events>();
        List<Events> EventsList = eventsRepository.findAll();
        LocalDate currentDate = LocalDate.now();
        if(EventsList.isEmpty())
            return null;

        Collections.sort(EventsList);

        for (Events event : EventsList) {
            LocalDate eventDate = event.getDate();
            if (eventDate.isAfter(currentDate)) {
                upcomingEvents.add(event);
            }
            if(upcomingEvents.size()==4)
                break;
        }

        return upcomingEvents;
    }
}
