package com.litongjava.tio.boot.demo.graphql.config;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import com.litongjava.jfinal.plugin.graphql.GQL;
import com.litongjava.tio.boot.demo.graphql.resolver.UserResolver;
import com.litongjava.tio.utils.hutool.FileUtil;
import com.litongjava.tio.utils.hutool.ResourceUtil;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

public class GraphQLConfig {

  public void graphQL() throws IOException {

    URL resource = ResourceUtil.getResource("schema.graphqls");
    StringBuilder text = FileUtil.readURLAsString(resource);

    String sdl = text.toString();

    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
    RuntimeWiring wiring = buildWiring();
    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);

    GraphQL grphql = GraphQL.newGraphQL(schema).build();

    GQL.setGraphQL(grphql);
  }

  private RuntimeWiring buildWiring() {
    @SuppressWarnings("rawtypes")
    Map<String, DataFetcher> dataFetchersMap = new HashMap<>();

    DataFetcher<?> userFetcher = env -> new UserResolver().getUser(env.getArgument("id"));

    dataFetchersMap.put("user", userFetcher);

    // builderFunction
    UnaryOperator<TypeRuntimeWiring.Builder> builderFunction = typeWiring -> {
      return typeWiring.dataFetchers(dataFetchersMap);
    };

    graphql.schema.idl.RuntimeWiring.Builder runtimeWiring = RuntimeWiring.newRuntimeWiring();

    return runtimeWiring.type("Query", builderFunction).build();
  }
}
