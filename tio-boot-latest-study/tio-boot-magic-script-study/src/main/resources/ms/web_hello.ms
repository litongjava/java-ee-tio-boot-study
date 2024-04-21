import com.litongjava.tio.http.common.HttpRequest
import com.litongjava.tio.http.common.HttpResponse
import com.litongjava.tio.boot.http.TioControllerContext
import com.litongjava.tio.utils.resp.RespVo

//调用静态方法
var request=TioControllerContext.getRequest();
var response=TioControllerContext.getResponse();


var sql = """select * from test_data where name=?"""
println(sql)

response.setJson(RespVo.ok(sql))
return response;
