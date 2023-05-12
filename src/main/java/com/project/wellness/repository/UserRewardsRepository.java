package com.project.wellness.repository;

import com.project.wellness.model.UserRewards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardsRepository extends MongoRepository<UserRewards, Integer>{
}
