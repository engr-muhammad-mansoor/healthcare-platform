package com.healthcare.uman.config.changelogs;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.jeasy.random.EasyRandom;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.healthcare.uman.model.user.GenderEnum;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@ChangeLog(order = "004")
public class UserDataChangelog {

    private final EasyRandom generator = new EasyRandom();

    @ChangeSet(order = "001", id = "insertProfessionals", author = "perrine")
    public void insertProfessionals(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("user");

        for (int i = 0; i < 1; i++) {
            List<Document> specialities = new ArrayList<>();
            specialities.add(new Document()
                    .append("speciality", new Document().append("name", "ORTHOPEDIQUE"))
                    .append("price", 60.0));

            List<Document> humanPreferences = new ArrayList<>();
            humanPreferences.add(new Document().append("name", "ENTHOUSIASTE"));
            humanPreferences.add(new Document().append("name", "RIGOUREUX"));
            humanPreferences.add(new Document().append("name", "COMMUNICATIF"));

            Document professionalDoc = new Document()
                    .append("type", "PRO")
                    .append("firstName", "Perrine")
                    .append("lastName", "Honore")
                    .append("email", "professional" + i + "@example.com")
                    .append("username", "professionalUser" + i)
                    .append("password", generator.nextObject(String.class))
                    .append("gender", generator.nextObject(GenderEnum.class).toString())
                    .append("birthDate", Instant.now())
                    .append("birthCity", "Laon")
                    .append("creationDate", Instant.now())
                    .append("updateDate", Instant.now())
                    .append("address", new Document()
                            .append("street", generator.nextObject(String.class))
                            .append("city", generator.nextObject(String.class))
                            .append("postalCode", "12345")
                            .append("country", "Country" + i))
                    .append("professionalCard", new Document()
                            .append("presentation", "I am a professional with specialization in " + generator.nextObject(String.class))
                            .append("specialities", specialities) // Ajouter les spécialités
                            .append("humanPreferences", humanPreferences)) // Ajouter les préférences
                    .append("active", true);

            collection.insertOne(professionalDoc);
        }

        for (int i = 0; i < 1; i++) {
            List<Document> specialities = new ArrayList<>();
            specialities.add(new Document()
                    .append("speciality", new Document().append("name", "SPORTIVE"))
                    .append("price", 60.0));

            List<Document> humanPreferences = new ArrayList<>();
            humanPreferences.add(new Document().append("name", "CALME"));
            humanPreferences.add(new Document().append("name", "ENERGIQUE"));

            Document professionalDoc = new Document()
                    .append("type", "PRO")
                    .append("firstName", "Vincent")
                    .append("lastName", "Ulvoas")
                    .append("email", "professional" + i + "@example.com")
                    .append("username", "professionalUser" + i)
                    .append("password", generator.nextObject(String.class))
                    .append("gender", generator.nextObject(GenderEnum.class).toString())
                    .append("birthDate", Instant.now())
                    .append("birthCity", "Laon")
                    .append("creationDate", Instant.now())
                    .append("updateDate", Instant.now())
                    .append("address", new Document()
                            .append("street", generator.nextObject(String.class))
                            .append("city", generator.nextObject(String.class))
                            .append("postalCode", "12345")
                            .append("country", "Country" + i))
                    .append("professionalCard", new Document()
                            .append("presentation", "I am a professional with specialization in " + generator.nextObject(String.class))
                            .append("specialities", specialities) // Ajouter les spécialités
                            .append("humanPreferences", humanPreferences)) // Ajouter les préférences
                    .append("active", true);

            collection.insertOne(professionalDoc);
        }
    }


    @ChangeSet(order = "002", id = "insertPatients", author = "perrine")
    public void insertPatients(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("user");
        for (int i = 0; i < 2; i++) {
            Document patientDoc = new Document()
                    .append("type", "PATIENT")
                    .append("firstName", generator.nextObject(String.class))
                    .append("lastName", generator.nextObject(String.class))
                    .append("email", "patient" + i + "@example.com")
                    .append("username", "patientUser" + i)
                    .append("password", generator.nextObject(String.class))
                    .append("gender", generator.nextObject(GenderEnum.class).toString())
                    .append("birthDate", Instant.now())
                    .append("birthCity", generator.nextObject(String.class))
                    .append("creationDate", Instant.now())
                    .append("updateDate", Instant.now())
                    .append("address", new Document()
                            .append("street", generator.nextObject(String.class))
                            .append("city", generator.nextObject(String.class))
                            .append("postalCode", "54321")
                            .append("country", "Country" + i))
                    .append("active", true);

            collection.insertOne(patientDoc);
        }
    }
}