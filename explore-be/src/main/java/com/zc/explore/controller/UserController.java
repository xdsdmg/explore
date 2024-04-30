package com.zc.explore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zc.explore.model.response.Response;
import com.zc.explore.model.response.ResultSingle;
import com.zc.explore.model.user.RegisterRequest;
import com.zc.explore.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  Response<ResultSingle> register(@RequestBody RegisterRequest req) {
    Exception e = null;

    try {
      userService.register(req);
    } catch (Exception e_) {
      log.error("[user] register failed, error: {}", e_);
      e = e_;
    }

    return Response.create(null, e);
  }

  @GetMapping("/activate/{token}")
  Response<ResultSingle> activate(@PathVariable(value = "token") String token) {
    Exception e = null;

    try {
      userService.activate(token);
    } catch (Exception e_) {
      log.error("[user] register failed, error: {}", e_);
      e = e_;
    }

    return Response.create(null, e);
  }
}
