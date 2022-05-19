package com.zrl.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.zrl.reggie.common.BaseContext;
import com.zrl.reggie.common.R;
import com.zrl.reggie.entity.Employee;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 检查用户是否登录
 * @author: zrl
 * @date: 2022/5/6 3:09
 * @description:
 */
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String RequestURI = request.getRequestURI();
        //定义不需要处理的路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };
        //2、判断本次请求是否需要处理
        Boolean check = check(urls, RequestURI);

        // 3、如果不需要处理，则直接放行
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        //4-1、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee")!=null){
            Long id = (Long) request.getSession().getAttribute("employee");
            BaseContext.setId(id);
            filterChain.doFilter(request,response);
            return ;
        }
        //4-2(移动端)、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user")!=null){
            Long id = (Long) request.getSession().getAttribute("user");
            BaseContext.setId(id);
            filterChain.doFilter(request,response);
            return ;
        }

        // 5、如果未登录则返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * 匹配路径
     * @param urls
     * @param uri
     * @return
     */
    public Boolean check(String[] urls,String uri){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, uri);
            if(match){
                return true;
            }
        }
        return false;
    }


}
