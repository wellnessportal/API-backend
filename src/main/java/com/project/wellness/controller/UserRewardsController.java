package com.project.wellness.controller;

import com.project.wellness.model.UserRewards;
import com.project.wellness.service.UserRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/userrewards")
public class UserRewardsController {
    private final UserRewardService userRewardService;

    @Autowired
    public UserRewardsController(UserRewardService userRewardService) {
        this.userRewardService = userRewardService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/{id}")
    public String addUserReward(@PathVariable String id){
        return userRewardService.addUserReward(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<UserRewards> viewAllUserRewards(){
        return userRewardService.findAllUserRewards();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{id}")
    public List<UserRewards> getUserRewards(@PathVariable String id){
        return userRewardService.findUserRewards(id);
    }

}
