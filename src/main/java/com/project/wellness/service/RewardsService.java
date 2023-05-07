package com.project.wellness.service;

import com.project.wellness.model.Rewards;
import com.project.wellness.repository.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RewardsService {
    private final RewardsRepository rewardsRepository;
    private final UsersService usersService;

    @Autowired
    public RewardsService(RewardsRepository rewardsRepository, UsersService usersService) {
        this.rewardsRepository = rewardsRepository;
        this.usersService = usersService;
    }

    public void addNewReward(Rewards rewards) {
        rewardsRepository.save(rewards);
    }

    public Rewards getRandomReward(String id, int rating) {
        if(rating%5==0){
            usersService.increaseUserRating(id);
            List<Rewards> rewardsList = rewardsRepository.findAll();
            Random rand = new Random();
            return rewardsList.get(rand.nextInt(rewardsList.size()));
        }
        return null;
    }

    public List<Rewards> getAllRewards() {
        return rewardsRepository.findAll();
    }
}
