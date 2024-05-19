package com.zc.explore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zc.explore.model.response.Response;
import com.zc.explore.model.response.ResultSingle;
import com.zc.explore.model.topiic.ListRequest;
import com.zc.explore.service.TopicService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/topic")
@Slf4j
public class TopicController {
  @Autowired
  private TopicService topicService;

  @GetMapping("/list")
  Response<ResultSingle> list(@RequestBody ListRequest req) {
    Exception e = null;

    try {
      topicService.list(req);
    } catch (Exception e_) {
      log.error("[topic] list failed, error: {}", e_);
      e = e_;
    }

    return Response.create(null, e);
  }
}
