package com.project.wellness.service;

import com.project.wellness.model.WaitingList;
import com.project.wellness.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListService {
    private WaitingListRepository waitingListRepository;
    private EventsService eventsService;

    @Autowired
    public WaitingListService(WaitingListRepository waitingListRepository, EventsService eventsService) {
        this.waitingListRepository = waitingListRepository;
        this.eventsService = eventsService;
    }

    public boolean addUserToWaitingList(WaitingList newPerson) {
        List<WaitingList> waitingList = waitingListRepository.findAll();
        for (WaitingList person : waitingList) {
            if(person.getEmail_id()== newPerson.getEmail_id() &&
            person.getEvent_id()==newPerson.getEvent_id()){
                return false;
            }
        }
        waitingListRepository.save(newPerson);
        return true;
    }

    public List<WaitingList> findAll(){
        return waitingListRepository.findAll();
    }

    public void deleteUserFromWaitingList(String email, int event_id){
        WaitingList person = getUserFromWaitingList(event_id);

        waitingListRepository.delete(person);
    }

    public WaitingList getUserFromWaitingList(int id) {
        List<WaitingList> waitingList = waitingListRepository.findAll();
        for (WaitingList person:waitingList) {
            if(id == person.getEvent_id()){
                return person;
            }
        }
        return null;
    }

    public void addUserFromWaitingList(int eventid) {
        WaitingList person = getUserFromWaitingList(eventid);
        eventsService.bookEvent(person.getEmail_id(), person.getEvent_id());
    }
}
