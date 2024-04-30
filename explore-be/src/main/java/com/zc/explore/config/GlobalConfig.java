package com.zc.explore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zc.explore.utils.Jwt;

import jakarta.annotation.PostConstruct;

@Configuration
public class GlobalConfig {
  @Autowired
  private Environment env;

  @PostConstruct
  public void init() {
    Jwt.init(env.getProperty("app.jwt.private_key_path"), env.getProperty("app.jwt.public_key_path"));
  }
}
