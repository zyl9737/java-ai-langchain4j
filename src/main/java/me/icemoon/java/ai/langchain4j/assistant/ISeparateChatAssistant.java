package me.icemoon.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description
 */
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "ollamaChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "calculatorTools" // 配置tools
)
public interface ISeparateChatAssistant {
    @SystemMessage(fromResource = "prompt-template.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @UserMessage("你是我的好朋友，请用粤语回答问题。{{message}}")
    String chat2(@MemoryId int memoryId, @V("message") String userMessage);

    @SystemMessage(fromResource = "prompt-template3.txt")
    String chat3(
            @MemoryId int memoryId,
            @UserMessage String userMessage,
            @V("username") String username,
            @V("age") int age
    );
}
