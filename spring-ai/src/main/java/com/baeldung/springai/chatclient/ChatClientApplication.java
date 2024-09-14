package com.baeldung.springai.chatclient;

import org.springframework.ai.autoconfigure.mistralai.MistralAiAutoConfiguration;
import org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		MistralAiAutoConfiguration.class,
		MongoDBAtlasVectorStoreAutoConfiguration.class
})
public class ChatClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChatClientApplication.class, args);
	}
}
