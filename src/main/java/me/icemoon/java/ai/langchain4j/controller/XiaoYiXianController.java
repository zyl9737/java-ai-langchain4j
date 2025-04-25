package me.icemoon.java.ai.langchain4j.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.icemoon.java.ai.langchain4j.assistant.IXiaoYiXianAgent;
import me.icemoon.java.ai.langchain4j.bean.ChatForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Yulong
 * @create 2025/4/23
 * @description 小医仙助手Controller
 */
@Tag(name="小医仙助手")
@RestController
@RequestMapping("/xiaoyixian")
public class XiaoYiXianController {

    @Autowired
    private IXiaoYiXianAgent xiaoYiXianAgent;

    @Operation(summary = "小医仙助手对话")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return xiaoYiXianAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
