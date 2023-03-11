package com.project.wellness.repository;

import com.project.wellness.model.MyEvents;
import com.project.wellness.model.MyEvents_ID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MyEventsRepository extends MongoRepository<MyEvents, MyEvents_ID> {
}
