package me.icemoon.java.ai.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import me.icemoon.java.ai.langchain4j.assistant.IAssistant;
import me.icemoon.java.ai.langchain4j.assistant.IMemoryChatAssistant;
import me.icemoon.java.ai.langchain4j.assistant.ISeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 测试ChatMemory,让大语言模型记住上下文
 */
@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private IAssistant assistant;
    @Autowired
    private QwenChatModel qwenChatModel;


    @Test
    public void testChatMemory() {
        String answer1 = assistant.chat("我是玉龙");
        System.out.println(answer1);
        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);
    }
    @Test
    public void testChatMemory2() {
        //第一轮对话
        UserMessage userMessage1 = UserMessage.userMessage("我是玉龙");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage1.text());
        //第二轮对话
        UserMessage userMessage2 = UserMessage.userMessage("你知道我是谁吗");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage1, userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage2.text());
    }
    @Test
    public void testChatMemory3() {
        // 创建ChatMemory
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // 创建AIService
        IAssistant assistant = AiServices
                .builder(IAssistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        // 调用service的接口
        String answer1 = assistant.chat("我是玉龙");
        System.out.println(answer1);
        String answer2 = assistant.chat("你知道我是谁吗");
        System.out.println(answer2);

    }
    @Autowired
    private IMemoryChatAssistant memoryChatAssistant;
    @Test
    public void testChatMemory4() {

        String answer1 = memoryChatAssistant.chat("我是玉龙");
        System.out.println(answer1);
        String answer2 = memoryChatAssistant.chat("你知道我是谁吗");
        System.out.println(answer2);

    }
    @Autowired
    private ISeparateChatAssistant separateChatAssistant;
    @Test
    public void testChatMemory5() {

        String answer1 = separateChatAssistant.chat(1, "我是玉龙");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(1,"你知道我是谁吗");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat(2,"你知道我是谁吗");
        System.out.println(answer3);
    }

}
