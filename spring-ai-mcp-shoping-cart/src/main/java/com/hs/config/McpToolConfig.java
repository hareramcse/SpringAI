package com.hs.config;

import java.util.List;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hs.tool.ShoppingCartMcpService;

@Configuration
public class McpToolConfig {

	@Bean
	public List<ToolCallback> toolCallbackProvider(ShoppingCartMcpService service) {
		return List.of(ToolCallbacks.from(service));
	}
}