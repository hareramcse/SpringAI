package com.hs.spring_ai_adviser.adviser;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor {

	@Override
	public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

		log.info("My Token Print Advisor is called:");
		log.info("Request: " + chatClientRequest.prompt().getContents());
		ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

		log.info("Token advisor: Response received from the model:");

		log.info("Response: " + chatClientResponse.chatResponse().getResult().getOutput().getText());

		log.info("Prompt Token : " + chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
		log.info("Completion Token : "
				+ chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
		log.info(
				"Total Token consumed: " + chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());

		return chatClientResponse;
	}

	@Override
	public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest,
			StreamAdvisorChain streamAdvisorChain) {

		Flux<ChatClientResponse> chatClientResponseFlux = streamAdvisorChain.nextStream(chatClientRequest);

		return chatClientResponseFlux;

	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
