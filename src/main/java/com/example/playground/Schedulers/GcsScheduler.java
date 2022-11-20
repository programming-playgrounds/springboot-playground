package com.example.playground.Schedulers;

import com.example.playground.service.google.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@Configuration
@Slf4j
public class GcsScheduler {
    //Authenticate to google before running locally: gcloud auth application-default login

    private Storage storage;

    public GcsScheduler(Storage storage) {
        this.storage = storage;
    }

    @Scheduled(fixedDelayString = "60000")
    public void test() throws IOException {
        storage.getBlobNames();
    }
}
