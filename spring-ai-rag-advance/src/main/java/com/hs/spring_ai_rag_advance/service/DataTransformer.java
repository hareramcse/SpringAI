package com.hs.spring_ai_rag_advance.service;

import java.util.List;

import org.springframework.ai.document.Document;

public interface DataTransformer {

	List<Document> transform(List<Document> documents);

}
