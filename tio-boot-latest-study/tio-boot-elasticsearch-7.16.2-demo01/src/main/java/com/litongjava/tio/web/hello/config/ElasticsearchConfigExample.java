package com.litongjava.tio.web.hello.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticsearchConfigExample {
  private static final String HOST = "192.168.1.2";
  private static final int PORT = 9200;
  private static final String USERNAME = "elastic";
  private static final String PASSWORD = "YourElasticPassword";

  public static RestHighLevelClient createClient() {
    // credentialsProvider
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USERNAME, PASSWORD));

    // httpHost
    HttpHost httpHost = new HttpHost(HOST, PORT, "http");

    // httpClientConfigCallback
    HttpClientConfigCallback httpClientConfigCallback = httpClientBuilder -> httpClientBuilder
        .setDefaultCredentialsProvider(credentialsProvider);

    // builder
    RestClientBuilder builder = RestClient.builder(httpHost).setHttpClientConfigCallback(httpClientConfigCallback);

    RestHighLevelClient client = new RestHighLevelClient(builder);
    return client;
  }
}
