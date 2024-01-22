package com.litongjava.tio.web.hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.config.MongoDb;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@RequestPath("/mongodb")
public class MongdbController {

  public String add() {
    // 获取数据库连接对象
    MongoDatabase database = MongoDb.getDatabase();
    // 获取集合
    MongoCollection<Document> collection = database.getCollection("user");
    // 要插入的数据
    Document document = new Document("name", "张三").append("sex", "男").append("age", 18);
    // 插入如下
    collection.insertOne(document);
    return "success";
  }

  public List<Document> list() {
    MongoDatabase database = MongoDb.getDatabase();
    MongoCollection<Document> collection = database.getCollection("user");
    // 查找集合中的所有文档,并遍历
    FindIterable<Document> iterable = collection.find();
    MongoCursor<Document> cursor = iterable.iterator();
    List<Document> lists = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doucment = cursor.next();
      lists.add(doucment);
    }
    return lists;
  }
  
  public List<Document> listDevices(){
    MongoDatabase database = MongoDb.getDatabase("penhub");
    MongoCollection<Document> collection = database.getCollection("devices");
    // 查找集合中的所有文档,并遍历
    FindIterable<Document> iterable = collection.find();
    MongoCursor<Document> cursor = iterable.iterator();
    List<Document> lists = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doucment = cursor.next();
      lists.add(doucment);
    }
    return lists;
  }
}
