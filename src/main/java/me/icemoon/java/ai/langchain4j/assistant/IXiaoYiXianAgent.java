package me.icemoon.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 小医仙助手
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaoYiXian",
        tools = "appointmentTools"
)
public interface IXiaoYiXianAgent {

    @SystemMessage(fromResource = "xiaoyixian-prompt-template.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
