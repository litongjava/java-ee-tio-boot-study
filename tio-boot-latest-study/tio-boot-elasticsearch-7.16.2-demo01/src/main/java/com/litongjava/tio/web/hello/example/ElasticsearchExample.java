package com.litongjava.tio.web.hello.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.litongjava.tio.web.hello.config.ElasticsearchConfigExample;

public class ElasticsearchExample {
  public static void main(String[] args) throws IOException {
    RestHighLevelClient client = ElasticsearchConfigExample.createClient();

    // 索引文档
    IndexRequest indexRequest = new IndexRequest("posts");
    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("user", "kimchy");
    jsonMap.put("postDate", "2020-01-01");
    jsonMap.put("message", "trying out Elasticsearch");
    indexRequest.source(jsonMap);
    IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    System.out.println("Index Response: " + indexResponse.getResult());

    // 搜索文档
    SearchRequest searchRequest = new SearchRequest("posts");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    System.out.println("Search Response: " + searchResponse.toString());

    client.close();
  }
}