package com.example.playground.MongoDb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "DateRecord")
@Getter
@Setter
public class DateRecord {
    private Date created;

    public DateRecord() {
        this.created = new Date();
    }

}
