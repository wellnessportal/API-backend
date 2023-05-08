package com.project.wellness.service;

import com.project.wellness.model.MyEvents;
import com.project.wellness.model.Users;
import com.project.wellness.model.WaitingList;
import com.project.wellness.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WaitingListService {
    private WaitingListRepository waitingListRepository;
    private EventsService eventsService;
    private MyEventsService myEventsService;
    private AdminService adminService;
    private UsersService usersService;

    @Autowired
    public WaitingListService(WaitingListRepository waitingListRepository, EventsService eventsService,
                              MyEventsService myEventsService, AdminService adminService,
                              UsersService usersService) {
        this.waitingListRepository = waitingListRepository;
        this.eventsService = eventsService;
        this.myEventsService = myEventsService;
        this.adminService = adminService;
        this.usersService = usersService;
    }

    public String addUserToWaitingList(WaitingList newPerson) {
        List<WaitingList> waitingList = waitingListRepository.findAll();
        for (WaitingList person : waitingList) {
            if(Objects.equals(person.getEmail_id(), newPerson.getEmail_id()) &&
                    person.getEvent_id()==newPerson.getEvent_id()){
                return "You were already added to the waiting list";
            }
        }
        List<Users> usersList = adminService.listBookedUsers(newPerson.getEvent_id());
        for (Users person: usersList) {
            if(Objects.equals(person.getEmail_id(), newPerson.getEmail_id())){
                return "You have already booked for this event";
            }
        }
        waitingListRepository.save(newPerson);
        return "You are added to the waiting list";
    }

    public List<WaitingList> findAll(){
        return waitingListRepository.findAll();
    }

    public void deleteUserFromWaitingList(int event_id){
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

    public boolean addUserFromWaitingList(int eventid) {
        WaitingList person = getUserFromWaitingList(eventid);
        if(person!=null) {
            usersService.increaseUserRating(person.getEmail_id());
            eventsService.bookEvent(person.getEmail_id(), person.getEvent_id());
            return true;
        }
        return false;
    }

    public int getTotalWaitSize() {
        return waitingListRepository.findAll().size();
    }
}
