package com.litongjava.tio.web.hello.config;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;

import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.hutool.StrUtil;

import lombok.extern.slf4j.Slf4j;

@AConfiguration
@Slf4j
public class ElasticsearchConfig {

  @ABean
  public static RestHighLevelClient createClient() {
    // basic info
    String urls = EnvUtils.get("elasticsearch.rest.urls");
    String username = EnvUtils.get("elasticsearch.rest.username");
    String password = EnvUtils.get("elasticsearch.rest.password");

    if (StrUtil.isEmpty(urls)) {
      return null;
    }

    // httpHost
    HttpHost httpHost = HttpHost.create(urls);

    // builder
    RestClientBuilder builder = RestClient.builder(httpHost);

    if (StrUtil.isNotEmpty(username)) {
      // credentialsProvider
      CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
      credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

      // httpClientConfigCallback
      HttpClientConfigCallback httpClientConfigCallback = httpClientBuilder -> httpClientBuilder
          .setDefaultCredentialsProvider(credentialsProvider);

      builder.setHttpClientConfigCallback(httpClientConfigCallback);
    }

    RestHighLevelClient client = new RestHighLevelClient(builder);

    TioBootServer.me().addDestroyMethod(() -> {
      try {
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    try {
      log.info("client:{}", client.ping(RequestOptions.DEFAULT));
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    return client;
  }
}
