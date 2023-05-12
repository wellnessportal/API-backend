package com.project.wellness.controller;

import com.project.wellness.model.Rewards;
import com.project.wellness.service.RewardsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/rewards")
public class RewardsController {
    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void addNewReward(@RequestBody Rewards rewards){
        rewardsService.addNewReward(rewards);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{id}/{rating}")
    public String getRandomReward(@PathVariable String id, @PathVariable int rating){
        return rewardsService.getRandomReward(id,rating);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Rewards> getAllRewards(){
        return rewardsService.getAllRewards();
    }

}
