package com.hs.spring_ai_rag_advance.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataLoaderImpl implements DataLoader {

	@Value("classpath:static/sample_data.json")
	private Resource jsonResource;

	@Value("classpath:static/cricket_rules.pdf")
	private Resource pdfResource;

	@Override
	public List<Document> loadDocumentsFromJson() {
		log.info("started loading json");
		JsonReader jsonReader = new JsonReader(jsonResource, "project");
		return jsonReader.read();
	}

	@Override
	public List<Document> loadDocumentsFromPdf() {
		log.info("started loading pdf data");
		TikaDocumentReader documentReader = new TikaDocumentReader(pdfResource);
		return documentReader.get();
	}
}
