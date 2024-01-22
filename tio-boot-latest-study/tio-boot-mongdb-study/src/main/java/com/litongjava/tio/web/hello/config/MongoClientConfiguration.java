package com.litongjava.tio.web.hello.config;

import java.util.ArrayList;
import java.util.List;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

@AConfiguration
public class MongoClientConfiguration {

  @AInitialization
  public void config() {

    String mongodbHost = "192.168.3.9";
    int mongodbPort = 27017;
    String mongodbSourceName = "admin";
    String mongodbUsername = "admin";
    String mongodbPassword = "Litong@123";

    List<ServerAddress> adds = new ArrayList<>();
    // ServerAddress()两个参数分别为 服务器地址 和 端口
    ServerAddress serverAddress = new ServerAddress(mongodbHost, mongodbPort);
    adds.add(serverAddress);
    List<MongoCredential> credentials = new ArrayList<>();

    // MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
    MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(mongodbUsername, mongodbSourceName,
        mongodbPassword.toCharArray());
    credentials.add(mongoCredential);

    // 通过连接认证获取MongoDB连接
    MongoClient mongoClient = new MongoClient(adds, credentials);

    // 连接到数据库
    MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodbSourceName);

    // 保持client and database;
    MongoDb.setClient(mongoClient);
    MongoDb.setDatabase(mongoDatabase);

    // 添加addDestroyMethod
    TioBootServer.addDestroyMethod(mongoClient::close);
  }
}
