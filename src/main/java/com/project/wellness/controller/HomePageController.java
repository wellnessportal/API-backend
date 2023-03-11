package com.project.wellness.controller;

import com.project.wellness.model.Events;
import com.project.wellness.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class HomePageController {
    private final HomePageService homePageService;
    @Autowired
    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/user/{id}/events")
    public ResponseEntity<List<Events>> getBookedEvents(@PathVariable String id) {
        List<Events> bookedEvents = homePageService.listBookedEvents(id);
        if(bookedEvents == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookedEvents, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/user/{id}/event/{eventid}")
    public ResponseEntity<String> cancelEvent(@PathVariable String id, @PathVariable int eventid) {
        String response = homePageService.cancelEvent(id, eventid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/upcomingevents")
    public ResponseEntity<List<Events>> getUpcomingEvents() {
        List<Events> upcomingevents = homePageService.getUpcomingEvents();
        if(upcomingevents == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(upcomingevents, HttpStatus.OK);
    }
}
