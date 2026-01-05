package com.healthcare.uman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.conversation.Conversation;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

}
