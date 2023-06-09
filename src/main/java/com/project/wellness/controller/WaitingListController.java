package com.project.wellness.controller;

import com.project.wellness.model.WaitingList;
import com.project.wellness.service.WaitingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/waitinglist")
public class WaitingListController {
    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public String addUserToWaitingList(@RequestBody WaitingList waitingList){
        return waitingListService.addUserToWaitingList(waitingList);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/all")
    public ResponseEntity<List<WaitingList>> getWaitingList(){
        List<WaitingList> waitingList = waitingListService.findAll();
        if(waitingList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(waitingList,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/{eventid}")
    public boolean addUserFromWaitingList(@PathVariable int eventid){
        return waitingListService.addUserFromWaitingList(eventid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{eventid}")
    public void removeUserFromWaitingList(@PathVariable int eventid){
        waitingListService.deleteUserFromWaitingList(eventid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public int getTotalWaitingSize(){
        return waitingListService.getTotalWaitSize();
    }

}
