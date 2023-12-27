package com.litongjava.tio.web.hello.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.litongjava.jfinal.plugin.ehcache.CacheKit;
import com.litongjava.tio.http.server.annotation.RequestPath;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@RequestPath("/ecache")
public class EhCacheController {
  public String[] getCacheNames() {
    String[] cacheNames = CacheKit.getCacheManager().getCacheNames();
    return cacheNames;
  }

  public Map<String, Map<String, Object>> getAllCacheValue() {
    CacheManager cacheManager = CacheKit.getCacheManager();
    String[] cacheNames = cacheManager.getCacheNames();
    Map<String, Map<String, Object>> retval = new HashMap<>(cacheNames.length);
    for (String name : cacheNames) {
      Map<String, Object> map = cacheToMap(cacheManager, name);
      retval.put(name, map);
    }
    return retval;

  }

  public Map<String, Object> getCacheValueByCacheName(String cacheName) {
    CacheManager cacheManager = CacheKit.getCacheManager();
    Map<String, Object> retval = cacheToMap(cacheManager, cacheName);
    return retval;
  }

  public Object getCacheValueByCacheNameAndCacheKey(String cacheName, String key) {
    Object object = CacheKit.get(cacheName, key);
    return object;
  }

  private Map<String, Object> cacheToMap(CacheManager cacheManager, String name) {
    Cache cache = cacheManager.getCache(name);
    @SuppressWarnings("unchecked")
    List<String> keys = cache.getKeys();
    Map<String, Object> map = new HashMap<>(keys.size());
    for (String key : keys) {
      Element element = cache.get(key);
      Object value = element.getObjectValue();
      map.put(key, value);
    }
    return map;
  }
}
