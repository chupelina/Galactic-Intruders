package com.example.demo.interceptors;

import com.example.demo.services.UserLogoutService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final UserLogoutService userLogoutService;

    public LoginInterceptor(UserLogoutService userLogoutService) {
        this.userLogoutService = userLogoutService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
       if(userLogoutService.includesUser(request.getRemoteUser())){
           userLogoutService.increaseOwns(request.getRemoteUser());
       }
    }
}
