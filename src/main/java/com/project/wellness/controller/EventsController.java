package com.project.wellness.controller;

import com.project.wellness.model.Events;
import com.project.wellness.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/events")
public class EventsController {
    private final EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void addEvent(@RequestBody Events events) {
        eventsService.addNewEvent(events);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/all")
    public ResponseEntity<List<Events>> getEvents() {
        List<Events> eventsList = eventsService.findAll();
        if (eventsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventsList, HttpStatus.OK);
    }
}
