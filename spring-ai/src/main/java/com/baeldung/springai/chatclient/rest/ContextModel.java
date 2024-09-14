package com.baeldung.springai.chatclient.rest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ContextModel {

	private final ChatModel chatModel;
	private final VectorStore vectorStore;

	ContextModel(ChatModel chatModel, EmbeddingModel embeddingModel) {
		this.chatModel = chatModel;
		this.vectorStore = new SimpleVectorStore(embeddingModel);
	}

	boolean putToContext() {
		Document doc = new Document("alexei is cool");
		vectorStore.add(List.of(doc));
		return true;
	}

	ChatResponse respondThroughContext(String userText) {
		ChatResponse response = ChatClient.builder(chatModel)
				.build().prompt()
				.advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
				.user(userText)
				.call()
				.chatResponse();
		System.out.println("Resp = " + response);
		return response;
	}

	ChatResponse respondSimply(String userText) {
		ChatResponse response = ChatClient.builder(chatModel)
				.build().prompt()
				.user(userText)
				.call()
				.chatResponse();
		System.out.println("Resp = " + response);
		return response;
	}

}
