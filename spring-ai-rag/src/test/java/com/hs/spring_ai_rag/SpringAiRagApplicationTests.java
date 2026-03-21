package com.hs.spring_ai_rag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hs.spring_ai_rag.helper.Helper;
import com.hs.spring_ai_rag.service.ChatService;

@SpringBootTest
class SpringAiRagApplicationTests {

	@Autowired
	private ChatService chatService;

	@Test
	void saveDataToVectorDatabase() throws InterruptedException {
		System.out.println("saving data to database");
		this.chatService.saveData(Helper.getData());
		System.out.println("data is saved successfully");
	}

}
