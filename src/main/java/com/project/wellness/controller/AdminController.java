package com.project.wellness.controller;

import com.project.wellness.model.Users;
import com.project.wellness.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id) {
        String response = adminService.deleteEvent(id);
        if(response == null) {
            return new ResponseEntity("Event not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/event/{id}")
    public ResponseEntity<List<Users>> getBookedUsers(@PathVariable int id){
        List<Users> usersList = adminService.listBookedUsers(id);
        if (usersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}

