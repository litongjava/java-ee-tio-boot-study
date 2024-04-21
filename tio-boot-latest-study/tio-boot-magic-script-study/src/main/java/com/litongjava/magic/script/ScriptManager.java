package com.litongjava.magic.script;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.ssssssss.script.MagicScript;
import org.ssssssss.script.MagicScriptContext;
import org.ssssssss.script.MagicScriptDebugContext;

import com.litongjava.tio.utils.hutool.FileUtil;
import com.litongjava.tio.utils.hutool.ResourceUtil;

public class ScriptManager {
  /**
   * 执行脚本
   */
  public static Object executeScript(String script, MagicScriptContext context) {
    script = (context instanceof MagicScriptDebugContext ? MagicScript.DEBUG_MARK : "") + script;
    MagicScript magicScript = MagicScript.create(script, null);
    // 执行脚本
    return magicScript.execute(context);
  }

  /**
   * 执行class-path下的脚步
   */
  @SuppressWarnings("unchecked")
  public static <T> T executeClasspathScript(String filename) throws Exception {
    // 读取脚本文件
    URL resource = ResourceUtil.getResource(filename);
    java.io.File file = FileUtil.file(resource.getFile());
    byte[] bytes = FileUtil.readBytes(file);
    String script = new String(bytes, StandardCharsets.UTF_8);

    // 创建脚本上下文
    MagicScriptContext context = new MagicScriptContext();
    
    // 执行脚本
    Object result = ScriptManager.executeScript(script, context);

    return (T) result;
  }
}
