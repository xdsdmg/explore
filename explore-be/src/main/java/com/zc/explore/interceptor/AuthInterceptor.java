/// Ref: https://www.tutorialspoint.com/spring_boot/spring_boot_interceptor.htm

package com.zc.explore.interceptor;

import java.io.PrintWriter;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// TODO: how to use context in Java?
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private void jweTokenEmptyHandler(HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        PrintWriter out = response.getWriter();
        out.write("{\"msg\": \"jwe token is empty\"}");
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler)
            throws Exception {
        try {
            if (request.getCookies() == null) {
                jweTokenEmptyHandler(response);
                return false;
            }

            String jweToken = null;

            for (Cookie c : request.getCookies()) {
                if ("Jwe-Token".equals(c.getName())) {
                    jweToken = c.getName();
                }
            }

            if (jweToken == null || jweToken.isEmpty()) {
                jweTokenEmptyHandler(response);
                return false;
            }
        } catch (Exception e) {
            log.error("[AuthInterceptor] preHandle failed, error: {}", e);
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                           @NonNull Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                @Nullable Exception exception) throws Exception {
    }
}
