package com.example.playground.iterator;

import com.example.playground.mongo.model.DateRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class DateRecorder {


    private MongoTemplate mongoTemplate;

    private DateRecord dateRecord;

    public DateRecorder(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Scheduled(fixedDelayString = "60000")
    public void init() {
        dateRecord = new DateRecord();
        log.info("Current Date: {}", dateRecord.getCreated());
        mongoTemplate.insert(dateRecord);
    }
}
