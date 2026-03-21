package com.hs.spring_ai_rag_advance.service;

import java.util.List;

public interface ChatService {

	String getResponse(String userQuery);

	void saveData(List<String> list);

}
