package com.example.playground.Schedulers;

import com.example.playground.service.google.Storage;
import org.springframework.scheduling.annotation.Scheduled;

public class GcsScheduler {

    private Storage storage;

    public GcsScheduler(Storage storage) {
        this.storage = storage;
    }

    @Scheduled(fixedDelayString = "60000")
    public void test() {
        storage.getBlobBucketNames();
    }
}
