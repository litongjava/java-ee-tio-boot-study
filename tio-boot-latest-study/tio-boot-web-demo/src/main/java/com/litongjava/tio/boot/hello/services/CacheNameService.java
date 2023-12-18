package com.litongjava.tio.boot.hello.services;

import java.util.ArrayList;
import java.util.List;

import com.litongjava.tio.boot.hello.model.CacheName;
import com.litongjava.tio.utils.time.Time;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CacheNameService {
  private CacheName demo = new CacheName("demo", null, Time.MINUTE_1 * 10);

  public List<CacheName> cacheNames() {
    List<CacheName> list = new ArrayList<>();
    list.add(demo);
    return list;
  }

}
