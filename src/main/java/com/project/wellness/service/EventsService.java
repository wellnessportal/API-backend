package com.project.wellness.service;

import com.project.wellness.model.Events;
import com.project.wellness.model.MyEvents;
import com.project.wellness.model.MyEvents_ID;
import com.project.wellness.repository.EventsRepository;
import com.project.wellness.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
    private EventsRepository eventsRepository;
    private SequenceGeneratorService sequenceGenerator;
    private MyEventsService myEventsService;
    private WaitingListRepository waitingListRepository;
    @Autowired
    public EventsService(EventsRepository eventsRepository, SequenceGeneratorService sequenceGenerator,
                         MyEventsService myEventsService) {
        this.eventsRepository = eventsRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.myEventsService = myEventsService;
    }

    private String makingExportLink(String sharingLink) {
        String exportLink;
        String fileId = sharingLink.substring(32,sharingLink.length()-17);
        exportLink = "https://drive.google.com/uc?export=view&id=" + fileId;
        return exportLink;
    }

    public void addNewEvent(Events events) {
        events.setEvent_id(sequenceGenerator.generateSequence(Events.SEQUENCE_NAME));
        events.setImage_link(makingExportLink(events.getImage_link()));
        eventsRepository.save(events);
    }

    public List<Events> findAll() { return eventsRepository.findAll(); }

    public List<Events> findByType(String type) { return eventsRepository.findByType(type);}

    public String bookEvent(String userid, int eventid) {
        Events event = eventsRepository.findById(eventid).orElse(null);
        if (event == null) {
            return null;
        }
        int capacity = event.getCapacity();
        if(capacity > 0) {
            event.setCapacity(capacity - 1);
            eventsRepository.save(event);
            myEventsService.addNewMyEvent(
                    new MyEvents(
                            new MyEvents_ID(userid, eventid)));
            return "Event successfully booked!";
        }
        else {
            return "Event is fully booked. No slots available!";
        }
    }

    public boolean checkBookingPossible(int eventid) {
         Events event = eventsRepository.findById(eventid).orElse(null);
         if(event.getCapacity()==0){
             return false;
         }
         return true;
    }
}
