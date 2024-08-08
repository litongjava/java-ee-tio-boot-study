package com.litongjava.tio.web.hello.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.utils.resp.RespVo;

@RequestPath("/elastic-search")
public class ElasticSearchController {

  public RespVo create() {
    RestHighLevelClient client = Aop.get(RestHighLevelClient.class);

    // 索引文档
    IndexRequest indexRequest = new IndexRequest("posts");
    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("user", "kimchy");
    jsonMap.put("postDate", "2020-01-01");
    jsonMap.put("message", "trying out Elasticsearch");
    indexRequest.source(jsonMap);
    IndexResponse indexResponse = null;
    try {
      indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    } catch (IOException e) {
      e.printStackTrace();
      return RespVo.fail(e.getMessage());
    }
    Result result = indexResponse.getResult();
    return RespVo.ok(result);
  }

  public RespVo search() {
    RestHighLevelClient client = Aop.get(RestHighLevelClient.class);

    // 搜索文档
    SearchRequest searchRequest = new SearchRequest("posts");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse=null;
    try {
      searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    } catch (IOException e) {
      e.printStackTrace();
      return RespVo.fail(e.getMessage());
    }
    return RespVo.ok(searchResponse);
  }
}
