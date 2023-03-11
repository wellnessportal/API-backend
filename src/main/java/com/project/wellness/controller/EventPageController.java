package com.project.wellness.controller;

import com.project.wellness.model.Events;
import com.project.wellness.service.EventsService;
import com.project.wellness.service.MyEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class EventPageController {
    private final EventsService eventsService;
    private final MyEventsService myEventsService;

    @Autowired
    public EventPageController(EventsService eventsService, MyEventsService myEventsService) {
        this.eventsService = eventsService;
        this.myEventsService = myEventsService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/events/type/{type}")
    public ResponseEntity<List<Events>> findByType(@PathVariable String type) {
        List<Events> eventsList = eventsService.findByType(type);
        if(eventsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventsList, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/users/{id}/bookevent/{eventid}")
    public ResponseEntity<String> bookEvent(@PathVariable String id, @PathVariable int eventid) {
        String response = eventsService.bookEvent(id, eventid);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/user/{id}/event/{eventid}")
    public boolean isBooked(@PathVariable String id, @PathVariable int eventid) {
        return myEventsService.isBooked(id, eventid);
    }
}
