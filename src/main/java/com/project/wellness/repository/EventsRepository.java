package com.project.wellness.repository;

import com.project.wellness.model.Events;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventsRepository extends MongoRepository<Events, Integer> {
    List<Events> findByType(String type);
}
