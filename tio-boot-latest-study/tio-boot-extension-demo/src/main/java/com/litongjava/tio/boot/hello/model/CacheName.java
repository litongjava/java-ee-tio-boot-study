package com.litongjava.tio.boot.hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CacheName {
  // `cacheName`（缓存名称）
  private String name;
  // `timeToLiveSeconds`（生存时间）和`timeToIdleSeconds`（闲置时间）。
  private Long timeToLiveSeconds;
  private Long timeToIdleSeconds;
}
