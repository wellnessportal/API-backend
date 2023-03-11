package com.project.wellness.service;

import com.project.wellness.model.Events;
import com.project.wellness.model.MyEvents;
import com.project.wellness.model.MyEvents_ID;
import com.project.wellness.model.Users;
import com.project.wellness.repository.EventsRepository;
import com.project.wellness.repository.MyEventsRepository;
import com.project.wellness.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private EventsRepository eventsRepository;
    private UsersRepository usersRepository;
    private MyEventsRepository myEventsRepository;

    @Autowired
    public AdminService(EventsRepository eventsRepository, UsersRepository usersRepository,
                        MyEventsRepository myEventsRepository) {
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
        this.myEventsRepository = myEventsRepository;
    }

    public String deleteEvent(int eventid) {
        Events event = eventsRepository.findById(eventid).orElse(null);
        if (event == null) {
            return "event not found in DB";
        }
        eventsRepository.delete(event);
        return "successfully deleted event";
    }

    public List<Users> listBookedUsers(int id){
        List<Users> bookedUsers = new ArrayList<Users>();
        List<String> usersIdList = new ArrayList<String>();
        List<MyEvents> myEventsList = myEventsRepository.findAll();
        for(MyEvents myEvents: myEventsList){
            MyEvents_ID myEvents_id = myEvents.getMyEvents_id();
            String userid = myEvents_id.getEmail_id();
            int eventid = myEvents_id.getEvent_id();

            if(eventid==id){
                usersIdList.add(userid);
            }
        }
        for(String userid: usersIdList){
            Users user = usersRepository.findById(userid).orElse(null);
            if(user!= null){
                bookedUsers.add(user);
            }
        }
        if(bookedUsers.isEmpty()){
            return null;
        }
        return  bookedUsers;
    }
}

