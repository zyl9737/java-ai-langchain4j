package me.icemoon.java.ai.langchain4j.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 带记忆的助手配置类,初级的智能体
 */
@Configuration
public class MemoryChatAssistantConfig {
    @Bean
    ChatMemory chatMemory() {
        // 设置聊天记忆记录的message数量
        return MessageWindowChatMemory.withMaxMessages(10);
    }
}
