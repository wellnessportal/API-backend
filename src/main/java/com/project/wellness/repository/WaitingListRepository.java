package com.project.wellness.repository;

import com.project.wellness.model.WaitingList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitingListRepository extends MongoRepository<WaitingList, String> {
}
