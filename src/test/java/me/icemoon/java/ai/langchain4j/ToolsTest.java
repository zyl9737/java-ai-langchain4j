package me.icemoon.java.ai.langchain4j;

import me.icemoon.java.ai.langchain4j.assistant.ISeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description Function Calling 测试
 */
@SpringBootTest
public class ToolsTest {
    @Autowired
    private ISeparateChatAssistant separateChatAssistant;

    @Test
    public void testCalculator() {
        String answer = separateChatAssistant.chat(5, "1 + 2 等于几， 4327943289479的平方根是多少？");
        // 3， 2080370.94
        System.out.println(answer);
    }
}
