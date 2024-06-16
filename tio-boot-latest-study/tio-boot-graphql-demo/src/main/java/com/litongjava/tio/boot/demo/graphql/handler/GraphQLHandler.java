package com.litongjava.tio.boot.demo.graphql.handler;

import java.util.Map;

import com.litongjava.jfinal.plugin.graphql.GQL;
import com.litongjava.tio.boot.http.TioControllerContext;
import com.litongjava.tio.boot.utils.TioRequestParamUtils;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.utils.resp.Resp;

import graphql.ExecutionInput;
import graphql.ExecutionResult;

public class GraphQLHandler {

  public HttpResponse graphql(HttpRequest httpRequest) {
    Map<String, Object> requestMap = TioRequestParamUtils.getRequestMap(httpRequest);
    
    String query = (String) requestMap.get("query");

    @SuppressWarnings("unchecked")
    Map<String, Object> variables = (Map<String, Object>) requestMap.get("variables");
    
    ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).variables(variables).build();

    ExecutionResult executionResult = GQL.execute(executionInput);

    Map<String, Object> specification = executionResult.toSpecification();

    System.out.println(specification);

    Resp resp = null;
    Object errors = specification.get("errors");
    if (errors != null) {
      resp = Resp.fail(errors);
    } else {
      Object data = specification.get("data");
      resp = Resp.ok(data);
    }

    HttpResponse response = TioControllerContext.getResponse();

    return response.setJson(resp);

  }

}
