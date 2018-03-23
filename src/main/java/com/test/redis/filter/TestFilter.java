package com.test.redis.filter;

import com.test.redis.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@WebFilter(urlPatterns = "/Blogs", filterName = "blosTest")
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!request.getRequestURI().equals("/login") && !request.getRequestURI().contains("/assets") && !request
                .getRequestURI().contains("/actuator")) {
            String token = request.getSession().getId().replace("-", "");
            if (StringUtils.isNotBlank(RedisUtil.getValue(token))) {
                RedisUtil.setValue(token, request.getSession().getId(), 30, TimeUnit.MINUTES);
                chain.doFilter(request, response);
            } else {
                response.sendRedirect("/login");
                //request.getRequestDispatcher("/login").forward(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
