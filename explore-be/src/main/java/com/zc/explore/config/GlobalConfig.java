/// Initialize global configuration.

package com.zc.explore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zc.explore.utils.Jwt;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class GlobalConfig {
  @Autowired
  private Environment env;

  @PostConstruct
  public void init() {
    try {
      // Initialize jwt configuration.
      Jwt.init(env.getProperty("app.jwt.private_key_path"), env.getProperty("app.jwt.public_key_path"));
    } catch (Exception e) {
      log.error("[init] initialize global configuration failed, error: {}\n", e);
      System.exit(1);
    }
  }
}
