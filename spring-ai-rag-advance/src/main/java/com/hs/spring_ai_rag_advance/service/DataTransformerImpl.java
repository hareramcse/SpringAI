package com.hs.spring_ai_rag_advance.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

@Service
public class DataTransformerImpl implements DataTransformer {
	@Override
	public List<Document> transform(List<Document> documents) {
		TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder().withChunkSize(300).withMinChunkSizeChars(400)
				.withMinChunkLengthToEmbed(10).withKeepSeparator(true).build();
		return tokenTextSplitter.transform(documents);

	}
}
