### 智能医疗客服：小医仙助手
大模型智能医疗客服系统，基于LangChain4j框架。

1. 支持多种大语言模型调用方式： 
- 官网API调用
- ollama本地调用 
- 阿里百炼调用

2. 采用mongoDB持久化聊天记忆，mysql持久化用户信息。

支持聊天记忆隔离，Function Calling函数调用（提出问题时，大语言模型会判断是否使用某个工具）。

3. 大语言模型的函数调用流程：
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

    User->>AI: 我想预约明天上午的神经内科
    AI->>Tool: 查询是否有号源 (ToolExecutionRequest: 神经内科, 2025-04-25, 上午)
    Tool->>AI: 返回查询结果 (ToolExecutionResult: true)
    AI->>User: 明天上午神经内科有号源可以预约哦！😊 请提供姓名、身份证号和指定医生
    User->>AI: 小明，123456200008120722，我想预约张医生
    AI->>Tool: 预约挂号 (ToolExecutionRequest: 小明, 123456200008120722, 神经内科, 2025-04-25, 上午, 张医生)
    Tool->>AI: 返回预约结果 (ToolExecutionResult: 预约成功)
    AI->>User: 您的预约已经成功安排好了！🎉 这里是您的预约详情...
    User->>AI: 请你帮我预约明天上午，神经内科，小明，123456200008120722，我想预约张医生
    AI->>Tool: 查询是否有号源 (ToolExecutionRequest: 神经内科, 2025-04-25, 上午)
    Tool->>AI: 返回查询结果 (ToolExecutionResult: true)
    AI->>Tool: 预约挂号 (ToolExecutionRequest: 小明, 123456200008120722, 神经内科, 2025-04-25, 上午, 张医生)
    Tool->>AI: 返回预约结果 (ToolExecutionResult: 您在相同的科室和时间已有预约)
    AI->>User: 小明您好，看起来您已经在明天上午的神经内科预约了张医生...
    User->>AI: 对不起，临时有事，请帮我取消明天上午的预约
    AI->>Tool: 取消预约挂号 (ToolExecutionRequest: 小明, 123456200008120722, 神经内科, 2025-04-25, 上午, 张医生)
    Tool->>AI: 返回取消结果 (ToolExecutionResult: 取消预约成功)
    AI->>User: 没问题的，小明。您的预约已经成功取消了...
```