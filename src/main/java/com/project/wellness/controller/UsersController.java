package com.project.wellness.controller;

import com.project.wellness.model.Users;
import com.project.wellness.service.EventsService;
import com.project.wellness.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UsersController {
    private final UsersService usersService;
    private final EventsService eventsService;
    @Autowired
    public UsersController(UsersService usersService, EventsService eventsService) {
        this.usersService = usersService;
        this.eventsService = eventsService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void addUser(@RequestBody Users user) {
        usersService.addNewUser(user);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/all")
    public List<Users> getAllUsers(){
        return usersService.findAll();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable("id") String id)throws IllegalStateException{
        usersService.deleteUser(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/getstatus/{id}")
    public String findUserStat(@PathVariable String id){ return usersService.findUserStat(id);}

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/increaserating/{id}")
    public String increaseUserRating(@PathVariable String id){
        return usersService.increaseUserRating(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/decreaserating/{id}")
    public String decreaseUserRating(@PathVariable String id){
        return usersService.decreaseUserRating(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/rating/{id}")
    public int getUserRating(@PathVariable String id){
        return usersService.getUserRating(id);
    }


}

