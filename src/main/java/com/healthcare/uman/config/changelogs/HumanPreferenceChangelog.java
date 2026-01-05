package com.healthcare.uman.config.changelogs;

import java.util.ArrayList;

import org.bson.Document;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.healthcare.uman.model.speciality.HumanPreferenceEnum;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@ChangeLog(order = "003")
public class HumanPreferenceChangelog {

    @ChangeSet(order = "001", id = "createHumanPreferences", author = "perrine")
    public void createSpecialities(MongoDatabase db) {
        MongoCollection<Document> collection;
        String collectionName = "humanPreference";

        boolean collectionExists = db.listCollectionNames().into(new ArrayList<>()).contains(collectionName);
        if (!collectionExists) {
            db.createCollection(collectionName);
        }

        collection = db.getCollection(collectionName);

        for (HumanPreferenceEnum humanPreferenceEnum : HumanPreferenceEnum.values()) {
            if (collection.countDocuments(Filters.eq("name", humanPreferenceEnum.name())) == 0) {
                collection.insertOne(new Document("name", humanPreferenceEnum.name()));
            }
        }
    }
}
