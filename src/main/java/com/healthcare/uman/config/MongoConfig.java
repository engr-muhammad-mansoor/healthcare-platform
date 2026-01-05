package com.healthcare.uman.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.healthcare.uman.repository")
public class MongoConfig extends MongoConfigurationSupport {

    @Value("${health.mongo.host:localhost}")
    String hostname;

    @Value("${health.mongo.username:''}")
    String username;

    @Value("${health.mongo.password:''}")
    String password;

    @Value("${health.mongo.db:test}")
    String dbname;

    @Primary
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(this.mongoDbFactory());
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient;
        mongoClient = MongoClients.create("mongodb://127.0.0.1:27017/test?retryWrites=true");
        // MongoDB connection string with credentials should be configured via properties file
        // Example: mongodb+srv://username:password@cluster.mongodb.net/database?retryWrites=true&w=majority
        return mongoClient;
    }

    @Override
    protected String getDatabaseName() {
        return dbname;
    }

    @Primary
    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(this.mongoClient(), this.getDatabaseName());
    }

}
