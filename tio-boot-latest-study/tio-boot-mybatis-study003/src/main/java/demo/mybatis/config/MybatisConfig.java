package demo.mybatis.config;

import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.AInitialization;
import com.litongjava.jfinal.aop.AopManager;
import com.litongjava.tio.boot.server.TioBootServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AConfiguration
public class MybatisConfig {

  @AInitialization
  public void sqlSessionFactory() {
    // 配置数据源
    PooledDataSource dataSource = new PooledDataSource();
    dataSource.setDriver("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://192.168.3.9/defaultdb");
    dataSource.setUsername("postgres");
    dataSource.setPassword("123456");

    // 创建 MyBatis 配置对象
    Configuration configuration = new Configuration();
    configuration.setLogImpl(StdOutImpl.class); // 设置日志输出到控制台

    // 注册类型别名
    configuration.getTypeAliasRegistry().registerAliases("demo.mybatis.model");

    // 配置环境，包括事务管理器和数据源
    Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource);
    configuration.setEnvironment(environment);

    // 添加 Mapper
    addMapper(configuration, "SystemAdminMapper.xml");

    try {
      // 构建 SqlSessionFactory
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
      // 构建 SqlSessionManager
      SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
      MySqlSessionManager.setSqlSessionManager(sqlSessionManager);

      // 将 Mapper 添加到 AOP 容器中
      addMapperToAop(sqlSessionManager);

      // 在应用关闭时，关闭 SqlSessionManager
      TioBootServer.me().addDestroyMethod(() -> {
        sqlSessionManager.close();
      });
    } catch (Exception e) {
      log.error("无法创建 SqlSessionManager", e);
      throw new RuntimeException(e);
    }
  }

  private void addMapper(Configuration configuration, String resource) {
    // 加载并解析 Mapper XML 文件
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resource);
    if (inputStream == null) {
      throw new RuntimeException("无法找到资源文件：" + resource);
    }
    Map<String, XNode> sqlFragments = configuration.getSqlFragments();
    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource, sqlFragments);
    xmlMapperBuilder.parse();
  }

  private void addMapperToAop(SqlSessionManager sqlSessionManager) {
    // 获取所有注册的 Mapper 接口
    Configuration configuration = sqlSessionManager.getConfiguration();
    for (Class<?> mapperClass : configuration.getMapperRegistry().getMappers()) {
      // 获取 Mapper 实例
      Object mapperInstance = sqlSessionManager.getMapper(mapperClass);
      // 将 Mapper 实例添加到 AOP 容器中
      AopManager.me().addSingletonObject(mapperClass, mapperInstance);
      log.info("Mapper [{}] added to AOP", mapperClass.getName());
    }
  }
}