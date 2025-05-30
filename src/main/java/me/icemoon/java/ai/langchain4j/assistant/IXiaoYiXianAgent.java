package me.icemoon.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 小医仙助手
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
//        chatModel = "ollamaChatModel",
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaoYiXian",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverXiaoYiXianPinecone"
)
public interface IXiaoYiXianAgent {

    @SystemMessage(fromResource = "xiaoyixian-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
