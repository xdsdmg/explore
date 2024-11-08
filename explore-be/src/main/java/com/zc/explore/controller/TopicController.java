package com.zc.explore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.explore.model.base.ResultList;
import com.zc.explore.model.response.*;
import com.zc.explore.model.topic.Topic;
import com.zc.explore.model.user.User;
import com.zc.explore.service.TopicService;
import com.zc.explore.service.UserService;
import com.zc.explore.utils.Type;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/topic")
@Slf4j
public class TopicController {
  @Autowired
  private TopicService topicService;
  @Autowired
  private UserService userService;

  @SuppressWarnings("rawtypes")
  @GetMapping("/list")
  Response<ResultArray> list(@RequestParam(value = "title_fuzzy_search", required = false) String titleFuzzySearch,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size) {

    Exception e = null;
    ResultList<Topic> result = null;

    try {
      result = topicService.list(titleFuzzySearch, Type.integer2int(page), Type.integer2int(size));
    } catch (Exception e_) {
      log.error("[topic] list failed, error: {}", e_);
      e = e_;
    }

    return Response.createArrayResponse(result, e);
  }

  @PostMapping("/auth/create")
  Response<ResultSingle> create(@RequestBody Topic req,
      @CookieValue(value = "Jwe-Token", defaultValue = "") String jweToken) {
    Exception e = null;

    try {
      /*
       * Add user's information to topic
       */
      User user = userService.getUserByJweToken(jweToken);
      req.setUserID(user.getId());

      topicService.create(req);
    } catch (Exception e_) {
      log.error("[topic] create topic failed, error: {}", e_);
      e = e_;
    }

    return Response.createSingleResponse(null, e);
  }
}
