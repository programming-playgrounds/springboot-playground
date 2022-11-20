package com.example.playground.service.gcp;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class config {

    private Storage storage;

    @Value("${gcs.host}")
    private String host;

//    @Value("${gcs.credentialsPath}")
//    private String credentialsPath;

    public config() throws Exception {
//        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("credentials/credentials.json"));
        storage = StorageOptions.newBuilder().setHost(this.host).build().getService();
    }

    public void createBlob(String blobname) {

    }

    public void getBlobBucketNames() {
        for (Bucket bucket : storage.list().iterateAll()) {
            log.info("Bucket Name: {}", bucket.getName());
        }
    }

    public void getBlobNames(String bucket) {
        for (Blob blob : storage.list(bucket).iterateAll()) {
            log.info("In Bucket, {}, there exist blob : {}", bucket, blob.getName());
        }
    }

}
