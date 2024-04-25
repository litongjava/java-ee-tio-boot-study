package groovy

import com.litongjava.tio.boot.http.TioControllerContext
import com.litongjava.tio.utils.resp.RespVo

// 获取请求和响应对象
def request = TioControllerContext.getRequest()
def response = TioControllerContext.getResponse()

def method = request.getRequestLine().getMethod()
print(method)
// 执行数据库查询
def sql = "select * from test_data where name=?"
println(sql)

// 设置响应对象
response.setJson(RespVo.ok(method))
return response