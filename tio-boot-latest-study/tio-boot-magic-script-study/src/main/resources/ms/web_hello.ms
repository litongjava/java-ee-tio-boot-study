import com.litongjava.tio.http.common.HttpRequest
import com.litongjava.tio.http.common.HttpResponse
import com.litongjava.tio.boot.http.TioRequestContext
import com.litongjava.model.body.RespBodyVo
import com.jfinal.kit.Kv
import com.litongjava.db.activerecord.Db

//调用静态方法
var request=TioRequestContext.getRequest();
var response=TioRequestContext.getResponse();


var sql = """select 1"""
println(sql)

var row=Db.findFirt(sql)
println(row.toKv())

var respBodyVo=RespBodyVo.ok(row.toKv())
response.setJson(respBodyVo)
return response;