package com.litongjava.tio.web.hello.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.Kv;
import com.litongjava.annotation.RequestPath;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.cache.AbsCache;
import com.litongjava.tio.utils.cache.CacheFactory;

@RequestPath("/internal")
public class InternalController {

  public RespBodyVo cache() {
    CacheFactory cacheFactory = TioBootServer.me().getServerTioConfig().getCacheFactory();
    Map<String, ? extends AbsCache> map = cacheFactory.getMap();

    Kv kv = Kv.by("cacheFactory", cacheFactory);
    kv.set("map", map);

    for (Map.Entry<String, ? extends AbsCache> entry : map.entrySet()) {
      String cacheName = entry.getKey();
      AbsCache cache = entry.getValue();
      Collection<String> keysCollection = cache.keysCollection();
      Map<String, Object> cacheMap = new HashMap<>();
      for (String key : keysCollection) {
        cacheMap.put(key, cache.get(key));
      }
      kv.set("cache_" + cacheName, cacheMap);
    }

    return RespBodyVo.ok(kv);
  }
}
