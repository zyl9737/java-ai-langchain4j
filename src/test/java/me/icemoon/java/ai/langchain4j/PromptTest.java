package me.icemoon.java.ai.langchain4j;

import me.icemoon.java.ai.langchain4j.assistant.IMemoryChatAssistant;
import me.icemoon.java.ai.langchain4j.assistant.ISeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 测试Prompt
 */
@SpringBootTest
public class PromptTest {
    @Autowired
    private ISeparateChatAssistant separateChatAssistant;

    @Test
    public void testPrompt() {
        String answer = separateChatAssistant.chat(5, "今天几号");
        System.out.println(answer);
    }

    @Autowired
    private IMemoryChatAssistant memoryChatAssistant;

    @Test
    public void testUserMessage() {
        String answer1 = memoryChatAssistant.chat("我是玉龙");
        System.out.println(answer1);

        String answer2 = memoryChatAssistant.chat("我是谁");
        System.out.println(answer2);

        String answer3 = memoryChatAssistant.chat("你是谁");
        System.out.println(answer3);
    }

    @Test
    public void testV() {
        String answer1 = separateChatAssistant.chat2(6, "我是玉龙");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat2(6, "我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserInfo() {
        // 这里的 username 和 age 是从数据库中获取的
        String answer = separateChatAssistant.chat3(7, "我是谁，多大了", "玉龙", 18);
        System.out.println(answer);
    }
}
