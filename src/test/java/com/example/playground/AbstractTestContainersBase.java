package com.example.playground;


import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
public abstract class AbstractTestContainersBase {

    static final MongoDBContainer MONGO_DB_CONTAINER;

    static {
        MONGO_DB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:5.0.13"));

        MONGO_DB_CONTAINER.start();

        System.setProperty("spring.data.mongodb.uri", MONGO_DB_CONTAINER.getReplicaSetUrl());
    }

}
