package com.project.wellness.service;

import com.project.wellness.model.UserRewards;
import com.project.wellness.model.Users;
import com.project.wellness.repository.UserRewardsRepository;
import com.project.wellness.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserRewardService {
    private final UserRewardsRepository userRewardsRepository;
    private final RewardsService rewardsService;
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    public UserRewardService(UserRewardsRepository userRewardsRepository, RewardsService rewardsService, UsersRepository usersRepository, UsersService usersService) {
        this.userRewardsRepository = userRewardsRepository;
        this.rewardsService = rewardsService;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    public String addUserReward(String id) {
        Users person = usersRepository.findById(id).orElse(null);
        if(person!=null){
            String r = rewardsService.getRandomReward(person.getHighestRating(), person.getRating());
            if(!Objects.equals(r, "no")) {
                UserRewards newReward = new UserRewards();
                List<UserRewards> userRewardsList = userRewardsRepository.findAll();
                if (userRewardsList.isEmpty()) {
                    newReward.setSlnum(1);
                } else {
                    newReward.setSlnum(userRewardsList.size() + 1);
                }
                newReward.setEmail(person.getEmail_id());
                newReward.setReward(r);
                userRewardsRepository.save(newReward);
                person.setHighestRating(person.getRating());
                usersRepository.save(person);
            }
            return r;
        }
        return "no";
    }

    public List<UserRewards> findAllUserRewards() {
        List<UserRewards> userRewardsList = userRewardsRepository.findAll();
        return userRewardsList;
    }

    public List<UserRewards> findUserRewards(String id) {
        List<UserRewards> userRewardsList = userRewardsRepository.findAll();
        List<UserRewards> idReward = new ArrayList<>();
        if(!userRewardsList.isEmpty()){
            for (UserRewards reward: userRewardsList) {
                if (Objects.equals(reward.getEmail(), id)) {
                    idReward.add(reward);
                }
            }
        }
        return idReward;
    }
}
