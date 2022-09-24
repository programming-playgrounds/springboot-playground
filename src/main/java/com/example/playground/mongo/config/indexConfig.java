package com.example.playground.mongo.config;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class indexConfig {

    private MongoTemplate mongoTemplate;

    public indexConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private void createIndex(MongoCollection<Document> collection,
                             Bson bson,
                             IndexOptions indexOptions) {
        collection.createIndex(bson, indexOptions);
    }

    @PostConstruct
    private void initIndexes() {
        MongoCollection<Document> mongoTestCollection = mongoTemplate.getCollection("DateRecord");
        createIndex(mongoTestCollection, Indexes.ascending("created"), new IndexOptions().expireAfter(5L, TimeUnit.HOURS));
        log.info("Created all MongoDb Indexes");
    }
}
