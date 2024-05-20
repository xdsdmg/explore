package com.zc.explore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.explore.model.response.Response;
import com.zc.explore.model.response.ResultSingle;
import com.zc.explore.service.TopicService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/topic")
@Slf4j
public class TopicController {
  @Autowired
  private TopicService topicService;

  @GetMapping("/list")
  Response<ResultSingle> list(
      @RequestParam(value = "title_fuzzy_search", required = false) String titleFuzzySearch,
      @RequestParam(value = "page", required = false) int page,
      @RequestParam(value = "size", required = false) int size) {
    Exception e = null;

    try {
      topicService.list(titleFuzzySearch, page, size);
    } catch (Exception e_) {
      log.error("[topic] list failed, error: {}", e_);
      e = e_;
    }

    return Response.create(null, e);
  }
}
