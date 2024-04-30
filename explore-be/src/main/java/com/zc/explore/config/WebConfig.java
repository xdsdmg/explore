/// Ref: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/interceptors.html

package com.zc.explore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zc.explore.interceptor.AuthInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(@NonNull InterceptorRegistry registry) {
    registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
  }

  @Override
  // TODO: need op
  public void addCorsMappings(@NonNull CorsRegistry registry) {
    registry.addMapping("/**");
  }
}
