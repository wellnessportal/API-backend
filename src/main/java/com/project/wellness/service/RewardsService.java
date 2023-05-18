package com.project.wellness.service;

import com.project.wellness.model.Rewards;
import com.project.wellness.repository.RewardsRepository;
import com.project.wellness.repository.UsersRepository;
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

    public String getRandomReward(int high, int rating) {
        if(((rating%5==0) && (high<rating)) || ((high==rating) && high == 100)){
            List<Rewards> rewardsList = rewardsRepository.findAll();
            Random rand = new Random();
            Rewards userReward = rewardsList.get(rand.nextInt(rewardsList.size()));
            String r = userReward.getReward();
            return r;
        }
        return "no";
    }

    public List<Rewards> getAllRewards() {
        return rewardsRepository.findAll();
    }
}
