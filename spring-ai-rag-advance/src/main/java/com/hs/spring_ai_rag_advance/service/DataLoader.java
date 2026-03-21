package com.hs.spring_ai_rag_advance.service;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DataLoader {

	List<Document> loadDocumentsFromJson();

	List<Document> loadDocumentsFromPdf();

}
