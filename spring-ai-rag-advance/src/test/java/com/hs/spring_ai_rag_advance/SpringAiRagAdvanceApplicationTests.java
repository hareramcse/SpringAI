package com.hs.spring_ai_rag_advance;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hs.spring_ai_rag_advance.service.DataLoader;
import com.hs.spring_ai_rag_advance.service.DataTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SpringAiRagAdvanceApplicationTests {

	@Autowired
	private DataLoader dataLoader;

	@Autowired
	private DataTransformer dataTransformer;

	@Autowired
	private VectorStore vectorStore;

	@Test
	void testDataLoader() {
		var documents = dataLoader.loadDocumentsFromJson();
		log.info("docments size" + documents.size());

		documents.forEach(item -> {
			log.info("docment: " + item);
		});

	}

	@Test
	void testPdfDataLoader() {
		List<Document> documents = dataLoader.loadDocumentsFromPdf();
		System.out.println(documents.size());
		documents.forEach(item -> {
			System.out.println(item);
			System.out.println("__________________-");
		});

		log.info("Read now going to transform");

		var transformedDocument = dataTransformer.transform(documents);
		System.out.println(transformedDocument.size());
		this.vectorStore.add(transformedDocument);
		System.out.println("Done");

	}

}
