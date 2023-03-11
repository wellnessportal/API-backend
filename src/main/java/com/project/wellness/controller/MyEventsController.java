package com.project.wellness.controller;

import com.project.wellness.model.MyEvents;
import com.project.wellness.service.MyEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/myevents")
public class MyEventsController {
    private final MyEventsService myEventsService;

    @Autowired
    public MyEventsController(MyEventsService myEventsService) {
        this.myEventsService = myEventsService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void addMyEvent(@RequestBody MyEvents myEvents) {
        myEventsService.addNewMyEvent(myEvents);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/all")
    public List<MyEvents> getMyEventsService() {
        return myEventsService.findAll();
    }
}
