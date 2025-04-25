package me.icemoon.java.ai.langchain4j;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yulong
 * @create 2025/4/24
 * @description
 */
@SpringBootTest
public class EmbeddingTest {

    @Autowired
    private EmbeddingModel embeddingModel;

    @Test
    public void testEmbeddingModel() {
        Response<Embedding> embed = embeddingModel.embed("；人生百态");
        System.out.println("向量维度： " + embed.content().vector().length);
        System.out.println("向量输出： " + embed.toString());
    }

    /**
     * 测试Pinecone向量存储
     */
    @Autowired
    private EmbeddingStore embeddingStore;
    @Test
    public void testPineconeEmbedded() {
        TextSegment segment1 = TextSegment.from("人生如戏");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("下暴雨了");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }

    @Test
    public void embeddingSearch() {
        // 提问，并将该问题转化为向量
        Embedding queryEmbedding = embeddingModel.embed("今天天气如何").content();
        // 创建搜索请求对象
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
//                .minScore(0.5)
                .build();
        // 根据搜索请求在向量数据库中进行相似度搜索
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);

        // 输出相似度的分
        System.out.println("相似度分数: " + embeddingMatch.score());
        // 输出相似文本
        System.out.println("相似文本: " + embeddingMatch.embedded().text());
    }

    @Test
    public void testUploadKnowledgeLibrary() {
        Document document1 = FileSystemDocumentLoader.loadDocument("E:/Documents/knowledge/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("E:/Documents/knowledge/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("E:/Documents/knowledge/神经内科.md");
        List<Document> documents = Arrays.asList(document1, document2, document3);
        // 文本向量化并存入向量数据库，将每个片段进行向量化，得到一个嵌入向量
        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documents);

    }

}
