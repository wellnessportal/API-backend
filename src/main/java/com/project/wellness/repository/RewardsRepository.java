package com.project.wellness.repository;

import com.project.wellness.model.Rewards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepository extends MongoRepository<Rewards, String> {
}
