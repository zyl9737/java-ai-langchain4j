### 智能医疗客服：小医仙助手
大模型智能医疗客服系统，基于LangChain4j框架。

1. 支持多种大语言模型调用方式： 
- 官网API调用
- ollama本地调用 
- 阿里百炼调用

2. 采用mongoDB持久化聊天记忆，mysql持久化用户信息, pinecone存储向量。

3. 支持聊天记忆隔离，Function Calling函数调用（提出问题时，大语言模型会判断是否使用某个工具）。

4. 大语言模型的函数调用流程：
```mermaid
sequenceDiagram
    participant User as 用户
    participant AI as AI
    participant Tool as 工具

    User->>AI: 用户提问 (UserMessage)
    AI->>AI: 解析用户提问，生成工具调用请求 (ToolExecutionRequest)
    AI->>Tool: 调用工具方法 (ToolExecutionRequest)
    Tool->>AI: 返回工具执行结果 (ToolExecutionResultMessage)
    AI->>AI: 根据工具结果生成响应
    AI->>User: 返回最终响应 (AiMessage)

```

以预约挂号的一次对话为例：
```mermaid
sequenceDiagram
    participant User as 用户
    participant AI as AI
    participant Tool as 工具

    rect rgb(191, 223, 255)
    rect rgb(255, 204, 153)
    rect rgb(221, 230, 221)

    User->>AI: 我想预约明天上午的神经内科
    AI->>Tool: 查询号源
    Tool-->>AI: 号源充足
    AI->>User: 明天上午神经内科有号源<br/>😊 请提供身份信息

    rect rgb(240, 240, 240)
    User->>AI: 小明，123456200008120722<br/>预约张医生
    AI->>Tool: 预约挂号请求
    Tool-->>AI: 预约成功
    AI->>User: 预约成功！🎉<br/>详情：...
    end

    User->>AI: 重复预约请求
    AI->>Tool: 二次号源验证
    Tool-->>AI: 存在冲突预约
    AI->>User: ⚠️ 重复预约提醒

    rect rgb(255, 230, 230)
    User->>AI: 请求取消预约
    AI->>Tool: 取消请求
    Tool-->>AI: 取消成功
    AI->>User: 已取消 ✅
    end

    end
    end
    end

```
5. 检索增强生成RAG
