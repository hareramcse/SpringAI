package com.hs.spring_ai_rag_advance.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.join.ConcatenationDocumentJoiner;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

	private ChatClient chatClient;

	@Value("classpath:/prompts/user-message.st")
	private Resource userMessage;

	@Value("classpath:/prompts/system-message.st")
	private Resource systemMessage;

	private VectorStore vectorStore;

	public ChatServiceImpl(ChatClient chatClient, VectorStore vectorStore) {
		this.chatClient = chatClient;
		this.vectorStore = vectorStore;
	}

	@Override
	public void saveData(List<String> list) {
		List<Document> documentList = list.stream().map(Document::new).toList();
		this.vectorStore.add(documentList);
	}

	@Override
	public String getResponse(String userQuery) {

		var advisor = RetrievalAugmentationAdvisor.builder()

				.queryTransformers(
						RewriteQueryTransformer.builder().chatClientBuilder(chatClient.mutate().clone()).build(),
						TranslationQueryTransformer.builder().chatClientBuilder(chatClient.mutate().clone())
								.targetLanguage("hindi").build()

				)
				.queryExpander(MultiQueryExpander.builder().chatClientBuilder(chatClient.mutate().clone())
						.numberOfQueries(3).build())
				.documentRetriever(VectorStoreDocumentRetriever.builder().vectorStore(vectorStore).topK(3)
						.similarityThreshold(0.3).build())
				.documentJoiner(new ConcatenationDocumentJoiner())
				.queryAugmenter(ContextualQueryAugmenter.builder().build())
//                .documentPostProcessors()

				.build();

		// actual call to llm
		return chatClient.prompt().advisors(advisor).user(userQuery).call().content();
	}

}
