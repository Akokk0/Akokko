package com.akokko.filter;

import com.akokko.dao.TokenMapper;
import com.akokko.pojo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    private static final String LOGIN_URL="http://akokko.com";

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.判断当前请求路径是否为登录请求,如果是,则直接放行
        String path = request.getRequestURI();
        if (UrlFilter.hasAuthorize(path)){
            //直接放行
            chain.doFilter(request, response);
            return;
        }

        //2.从cookie中获取cookie的值,如果该值不存在,拒绝本次访问
        Cookie[] cookies = request.getCookies();
        Cookie uid = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("uid".equals(cookie.getName())) {
                    if (cookie.getValue() == null){
                        //跳转登录页面
                        this.toLoginPage(LOGIN_URL + "?FROM=" + request.getRequestURI(), response);
                    }
                    uid = cookie;
                    //3.从数据库中获取cookie的值,如果该值不存在,拒绝本次访问
                    Token userToken = new Token();
                    userToken.setCookie(uid.getValue());
                    Token token = tokenMapper.selectOne(userToken);
                    if (token == null){
                        //拒绝访问
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
                        this.toLoginPage(LOGIN_URL, response);
                    }

                    //4.对当前的请求对象进行增强,让它会携带令牌的信息
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        this.toLoginPage(LOGIN_URL, response);
    }

    //跳转登录页面
    private void toLoginPage(String loginUrl, HttpServletResponse response) {
        response.setStatus(HttpStatus.SEE_OTHER.value());
        response.setHeader("Location", loginUrl);
        try {
            response.sendRedirect(loginUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return;
        }
    }
}
