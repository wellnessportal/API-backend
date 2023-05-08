package com.project.wellness.repository;

import com.project.wellness.model.WaitingList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WaitingListRepository extends MongoRepository<WaitingList, Integer> {
}
