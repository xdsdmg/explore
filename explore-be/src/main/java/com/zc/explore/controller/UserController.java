package com.zc.explore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zc.explore.model.response.Response;
import com.zc.explore.model.response.ResultSingle;
import com.zc.explore.model.user.RegisterRequest;
import com.zc.explore.model.user.LoginInfo;
import com.zc.explore.model.user.LoginRequest;
import com.zc.explore.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/auth/info")
    Response<ResultSingle> info(@CookieValue(value = "Jwe-Token", defaultValue = "") String jweToken) {
        Exception e = null;
        LoginInfo info = null;

        log.info("hello world");

        try {
            info = userService.info(jweToken);
        } catch (Exception e_) {
            log.error("[user] get user info failed, error: {}", e_);
            e = e_;
        }

        return Response.createSingleResponse(info, e);
    }

    @PostMapping("/register")
    Response<ResultSingle> register(@RequestBody RegisterRequest req) {
        Exception e = null;

        try {
            userService.register(req);
        } catch (Exception e_) {
            log.error("[user] register failed, error: {}", e_);
            e = e_;
        }

        return Response.createSingleResponse(null, e);
    }

    @GetMapping("/activate/{token}")
    Response<ResultSingle> activate(@PathVariable(value = "token") String token) {
        Exception e = null;

        try {
            userService.activate(token);
        } catch (Exception e_) {
            log.error("[user] activate failed, error: {}", e_);
            e = e_;
        }

        return Response.createSingleResponse(null, e);
    }

    @PostMapping("/login")
    Response<ResultSingle> login(@RequestBody LoginRequest req, HttpServletResponse resp) {
        Exception e = null;
        String jweToken = null;

        try {
            jweToken = userService.login(req);
        } catch (Exception e_) {
            log.error("[user] login failed, error: {}", e_);
            e = e_;
        }

        if (e == null && jweToken != null) {
            Cookie cookie = new Cookie("Jwe-Token", jweToken);

            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);

            resp.addCookie(cookie);
        }

        return Response.createSingleResponse(null, e);
    }
}
