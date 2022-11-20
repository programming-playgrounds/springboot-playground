package com.example.playground.service.google;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Storage {

    private com.google.cloud.storage.Storage storage;

    @Value("${gcs.host}")
    private String host;

    @Value("${gcs.default.bucket}")
    private String bucket;

    public Storage() throws Exception {
        storage = StorageOptions.newBuilder().setHost(host).build().getService();
    }

    public void getBlobBucketNames() {
        for (Bucket bucket : storage.list().iterateAll()) {
            log.info("Bucket Name: {}", bucket.getName());
        }
    }

    public void getBlobNames() {
        log.info("bucket: {}", bucket);
        for (Blob blob : storage.list(bucket).iterateAll()) {
            log.info("In Bucket, {}, there exist blob : {}", bucket, blob.getName());
        }
    }

}
