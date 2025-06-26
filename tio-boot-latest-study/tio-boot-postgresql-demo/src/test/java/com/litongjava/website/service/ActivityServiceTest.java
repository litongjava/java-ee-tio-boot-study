package com.litongjava.website.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import org.junit.Test;

import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.tio.boot.postgresql.demo.config.DbConfig;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.snowflake.SnowflakeIdUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActivityServiceTest {

  @Test
  public void addActivity() {
    // 1. 初始化数据库连接
    EnvUtils.load();
    new DbConfig().config();

    // 2. 获取当前服务器的系统时区
    ZoneId zoneId = ZoneId.systemDefault(); // 例如: Asia/Shanghai

    // 3. 创建一个基于当前时间的 OffsetDateTime 对象
    Instant startInstant = Instant.now();
    OffsetDateTime startTime = startInstant.atZone(zoneId).toOffsetDateTime();

    // 4. 创建一个24小时后的结束时间
    Instant endInstant = startInstant.plus(1, java.time.temporal.ChronoUnit.DAYS);
    OffsetDateTime endTime = endInstant.atZone(zoneId).toOffsetDateTime();

    // 5. 构建记录并保存
    Row row = new Row().set("id", SnowflakeIdUtils.id())
        //
        .set("title", "Test Activity").set("start_time", startTime).set("end_time", endTime);

    boolean save = Db.save("activity", row);
    log.info("save result:{}", save);
    // 底层执行的SQL: 
    // insert into "activity"("id", "title", "start_time", "end_time") values(?, ?, ?, ?)
  }
  
  @Test
  public void listActivity() {
    // 1. 初始化数据库连接
    EnvUtils.load();
    new DbConfig().config();
    
    // 查询所有记录
    List<Row> records = Db.findAll("activity");
    
    // 打印结果
//    for (Row row : records) {
//      Object startTimeObj = row.get("start_time");
//      if (startTimeObj != null) {
//        log.info("Key: start_time, Value: {}, Class: {}", startTimeObj, startTimeObj.getClass().getName());
//      }
//    }
    
//    for (Row row : records) {
//      Timestamp ts = row.getTimestamp("start_time");
//      if (ts != null) {
//        // 转换为 OffsetDateTime，并假设数据库连接返回的是系统默认时区的时间
//        OffsetDateTime startTime = ts.toInstant().atOffset(ZoneOffset.UTC);
//        System.out.println(startTime);
//        // 或者转换为特定时区的时间
//        // ZonedDateTime zonedStartTime = ts.toInstant().atZone(ZoneId.of("Asia/Shanghai"));
//      }
//    }
    
    for (Row row : records) {
      OffsetDateTime startTime = row.getOffsetDateTime("start_time");
      System.out.println(startTime);
    }
  
  }
}