package me.icemoon.java.ai.langchain4j.assistant;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 助手接口
 */
@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel")
public interface IAssistant {
    String chat(String userMessage);
}
