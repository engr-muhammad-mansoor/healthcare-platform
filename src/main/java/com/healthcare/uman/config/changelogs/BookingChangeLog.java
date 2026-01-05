package com.healthcare.uman.config.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog(order = "001")
public class BookingChangeLog {

    @ChangeSet(order = "001", id = "createBookingCollection", author = "perrine")
    public void createBookingCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "booking");
    }

    @ChangeSet(order = "002", id = "createConversationCollection", author = "perrine")
    public void createConversationCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "conversation");
    }

    @ChangeSet(order = "003", id = "createHumanPreferenceCollection", author = "perrine")
    public void createHumanPreferenceCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "humanPreference");
    }

    @ChangeSet(order = "004", id = "createMedicalCollection", author = "perrine")
    public void createMedicalCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "medical");
    }

    @ChangeSet(order = "005", id = "createPricingCollection", author = "perrine")
    public void createPricingCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "pricing");
    }

    @ChangeSet(order = "006", id = "createReviewCollection", author = "perrine")
    public void createReviewCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "review");
    }

    @ChangeSet(order = "007", id = "createSpecialityCollection", author = "perrine")
    public void createSpecialityCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "speciality");
    }

    @ChangeSet(order = "008", id = "createUserCollection", author = "perrine")
    public void createUserCollection(MongoDatabase db) {
        createCollectionIfNotExists(db, "user");
    }

    private void createCollectionIfNotExists(MongoDatabase db, String collectionName) {
        if (!db.getCollection(collectionName).find().iterator().hasNext()) {
            db.createCollection(collectionName);
        }
    }
}
