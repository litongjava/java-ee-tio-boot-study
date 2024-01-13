package com.litongjava.tio.web.hello.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.config.utils.TDUtils;
import com.taosdata.jdbc.ws.TSWSPreparedStatement;

import lombok.Cleanup;

@RequestPath("/tdeingien/test")
public class TbEngineTestController {

  public String connection() throws SQLException {
    @Cleanup
    Connection connection = TDUtils.ds.getConnection();
    String string = connection.toString();
    return string;
  }

  /**
   * 创建表和数据库
   * @throws SQLException
   */
  public String init() throws SQLException {
    int BINARY_COLUMN_SIZE = 30;
    String[] schemaList = {
        "create table stable1(ts timestamp, f1 tinyint, f2 smallint, f3 int, f4 bigint) tags(t1 tinyint, t2 smallint, t3 int, t4 bigint)",
        "create table stable2(ts timestamp, f1 float, f2 double) tags(t1 float, t2 double)",
        "create table stable3(ts timestamp, f1 bool) tags(t1 bool)",
        "create table stable4(ts timestamp, f1 binary(" + BINARY_COLUMN_SIZE + ")) tags(t1 binary(" + BINARY_COLUMN_SIZE
            + "))",
        "create table stable5(ts timestamp, f1 nchar(" + BINARY_COLUMN_SIZE + ")) tags(t1 nchar(" + BINARY_COLUMN_SIZE
            + "))"
        //
    };

    @Cleanup
    Connection conn = TDUtils.ds.getConnection();

    try (Statement stmt = conn.createStatement()) {
      stmt.execute("drop database if exists test_ws_parabind");
      stmt.execute("create database if not exists test_ws_parabind");
      stmt.execute("use test_ws_parabind");
      for (int i = 0; i < schemaList.length; i++) {
        stmt.execute(schemaList[i]);
      }
    }
    return "success";
  }

  /**
   * init类型参数查询
   */
  public String bindInteger() throws SQLException {
    String sql = "insert into ? using stable1 tags(?,?,?,?) values(?,?,?,?,?)";
    int numOfSubTable = 10, numOfRow = 10;
    Random random = new Random(System.currentTimeMillis());
    @Cleanup
    Connection conn = TDUtils.ds.getConnection();

    try (TSWSPreparedStatement pstmt = conn.prepareStatement(sql).unwrap(TSWSPreparedStatement.class)) {

      pstmt.execute("use test_ws_parabind");

      for (int i = 1; i <= numOfSubTable; i++) {
        // set table name
        pstmt.setTableName("t1_" + i);
        // set tags
        pstmt.setTagByte(1, Byte.parseByte(Integer.toString(random.nextInt(Byte.MAX_VALUE))));
        pstmt.setTagShort(2, Short.parseShort(Integer.toString(random.nextInt(Short.MAX_VALUE))));
        pstmt.setTagInt(3, random.nextInt(Integer.MAX_VALUE));
        pstmt.setTagLong(4, random.nextLong());
        // set columns
        long current = System.currentTimeMillis();
        for (int j = 0; j < numOfRow; j++) {
          pstmt.setTimestamp(1, new Timestamp(current + j));
          pstmt.setByte(2, Byte.parseByte(Integer.toString(random.nextInt(Byte.MAX_VALUE))));
          pstmt.setShort(3, Short.parseShort(Integer.toString(random.nextInt(Short.MAX_VALUE))));
          pstmt.setInt(4, random.nextInt(Integer.MAX_VALUE));
          pstmt.setLong(5, random.nextLong());
          pstmt.addBatch();
        }
        pstmt.executeBatch();
      }
    }
    return "success";
  }

  public String bindFloat() throws SQLException {
    String sql = "insert into ? using stable2 tags(?,?) values(?,?,?)";
    Random random = new Random(System.currentTimeMillis());
    @Cleanup
    Connection conn = TDUtils.ds.getConnection();

    int numOfSubTable = 10, numOfRow = 10;

    try (TSWSPreparedStatement pstmt = conn.prepareStatement(sql).unwrap(TSWSPreparedStatement.class)) {

      pstmt.execute("use test_ws_parabind");

      for (int i = 1; i <= numOfSubTable; i++) {
        // set table name
        pstmt.setTableName("t2_" + i);
        // set tags
        pstmt.setTagFloat(1, random.nextFloat());
        pstmt.setTagDouble(2, random.nextDouble());
        // set columns
        long current = System.currentTimeMillis();
        for (int j = 0; j < numOfRow; j++) {
          pstmt.setTimestamp(1, new Timestamp(current + j));
          pstmt.setFloat(2, random.nextFloat());
          pstmt.setDouble(3, random.nextDouble());
          pstmt.addBatch();
        }
        pstmt.executeBatch();
      }
    }

    return "success";
  }

  public String bindBoolean() throws SQLException {
    String sql = "insert into ? using stable3 tags(?) values(?,?)";
    int numOfSubTable = 10, numOfRow = 10;
    Random random = new Random(System.currentTimeMillis());
    @Cleanup
    Connection conn = TDUtils.ds.getConnection();
    try (TSWSPreparedStatement pstmt = conn.prepareStatement(sql).unwrap(TSWSPreparedStatement.class)) {
      for (int i = 1; i <= numOfSubTable; i++) {
        // set table name
        pstmt.setTableName("t3_" + i);
        // set tags
        pstmt.setTagBoolean(1, random.nextBoolean());
        // set columns
        long current = System.currentTimeMillis();
        for (int j = 0; j < numOfRow; j++) {
          pstmt.setTimestamp(1, new Timestamp(current + j));
          pstmt.setBoolean(2, random.nextBoolean());
          pstmt.addBatch();
        }
        pstmt.executeBatch();
      }
    }
    return "success";
  }

  public String bindBytes() throws SQLException {
    String sql = "insert into ? using stable4 tags(?) values(?,?)";
    int numOfSubTable = 10, numOfRow = 10;
    @Cleanup
    Connection conn = TDUtils.ds.getConnection();
    try (TSWSPreparedStatement pstmt = conn.prepareStatement(sql).unwrap(TSWSPreparedStatement.class)) {

      for (int i = 1; i <= numOfSubTable; i++) {
        // set table name
        pstmt.setTableName("t4_" + i);
        // set tags
        pstmt.setTagString(1, new String("abc"));

        // set columns
        long current = System.currentTimeMillis();
        for (int j = 0; j < numOfRow; j++) {
          pstmt.setTimestamp(1, new Timestamp(current + j));
          pstmt.setString(2, "abc");
          pstmt.addBatch();
        }
        pstmt.executeBatch();
      }
    }
    return "success";
  }

  public String bindString() throws SQLException {
    String sql = "insert into ? using stable5 tags(?) values(?,?)";
    int numOfSubTable = 10, numOfRow = 10;
    @Cleanup
    Connection conn = TDUtils.ds.getConnection();

    try (TSWSPreparedStatement pstmt = conn.prepareStatement(sql).unwrap(TSWSPreparedStatement.class)) {

      for (int i = 1; i <= numOfSubTable; i++) {
        // set table name
        pstmt.setTableName("t5_" + i);
        // set tags
        pstmt.setTagNString(1, "California.SanFrancisco");

        // set columns
        long current = System.currentTimeMillis();
        for (int j = 0; j < numOfRow; j++) {
          pstmt.setTimestamp(0, new Timestamp(current + j));
          pstmt.setNString(1, "California.SanFrancisco");
          pstmt.addBatch();
        }
        pstmt.executeBatch();
      }
    }
    return "success";
  }

  public List<Record> selectStable1() throws SQLException {
    String sql = "select * from test.stable1";
    // List<Record> records = Db.find(sql);
    // return records;
    @Cleanup
    Connection conn = TDUtils.ds.getConnection();
    @Cleanup
    Statement stmt = conn.createStatement();
    stmt.execute("use test_ws_parabind");

    ResultSet resultSet = stmt.executeQuery(sql);

    return null;
  }
}
