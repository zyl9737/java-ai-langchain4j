package me.icemoon.java.ai.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import me.icemoon.java.ai.langchain4j.assistant.IAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 测试AIService
 */
@SpringBootTest
public class AIServiceTest {
    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChat() {
        // 创建AIService
        IAssistant assistant = AiServices.create(IAssistant.class, qwenChatModel);
        // 调用service的接口
        String answer = assistant.chat("Hello");
        System.out.println(answer);
    }
    @Autowired
    private IAssistant assistant;
    @Test
    public void testChat2() {
        // 调用service的接口
        String answer = assistant.chat("哈喽");
        System.out.println(answer);
    }
}
