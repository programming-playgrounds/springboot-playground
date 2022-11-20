package com.example.playground;


import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public abstract class AbstractTestContainersBase {

    static final MongoDBContainer MONGO_DB_CONTAINER;

    static final GenericContainer<?> GOOGLE_CLOUD_STORAGE_CONTAINER;

    static {
        MONGO_DB_CONTAINER = new MongoDBContainer("mongo:5.0.13");
        GOOGLE_CLOUD_STORAGE_CONTAINER =
                new GenericContainer<>("fsouza/fake-gcs-server")
                        .withExposedPorts(4443)
                        .withClasspathResourceMapping("data", "/data", BindMode.READ_WRITE)
                        .withCreateContainerCmdModifier(
                                createContainerCmd -> createContainerCmd.withEntrypoint("/bin/fake-gcs-server", "-data", "/data", "-scheme", "http"));

        MONGO_DB_CONTAINER.start();
        GOOGLE_CLOUD_STORAGE_CONTAINER.start();

        System.setProperty("spring.data.mongodb.uri", MONGO_DB_CONTAINER.getReplicaSetUrl());
        System.setProperty("gcs.host", "http://" + GOOGLE_CLOUD_STORAGE_CONTAINER.getHost() + ":" + GOOGLE_CLOUD_STORAGE_CONTAINER.getFirstMappedPort());
    }

}
