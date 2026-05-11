package com.example.reactback.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 모든 도메인 허용 (보안상 특정 도메인만 지정하는 것이 좋습니다)
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 허용할 메서드 (GET, POST, PUT, DELETE 등)
        response.setHeader("Access-Control-Allow-Methods", "*");
        // 캐시 유지 시간
        response.setHeader("Access-Control-Max-Age", "3600");
        // 허용할 헤더
        response.setHeader("Access-Control-Allow-Headers", "*");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
