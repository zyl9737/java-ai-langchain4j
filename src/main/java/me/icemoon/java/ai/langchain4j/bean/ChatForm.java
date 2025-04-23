package me.icemoon.java.ai.langchain4j.bean;

import lombok.Data;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 封装对话对象
 */
@Data
public class ChatForm {
    private Long memoryId; // 对话ID

    private String message; // 用户问题
}
