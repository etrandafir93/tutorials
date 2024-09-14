package com.baeldung.springai.chatclient.rest;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/articles")
public class BlogsController {

	private final ContextModel model;
	private final ChatModel chatModel;


	public BlogsController(ContextModel model, ChatModel chatModel) {
		this.model = model;
		this.chatModel = chatModel;
	}

	@GetMapping
	String get() {
		return askWithoutContext("tell me article names to read if i want to learn about SpringBoot")
				.getResult()
				.toString();
	}


	ChatResponse askAgainstContext(String question) {
		var resp = model.respondThroughContext(question);
		System.out.println("Resp = " + resp);
		return resp;
	}

	ChatResponse askWithoutContext(String question) {
		var resp = model.respondSimply(question);
		System.out.println("Resp = " + resp);
		return resp;
	}

}
