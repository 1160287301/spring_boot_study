package com.hello.filter;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 这里直接用@WebFilter就可以进行配置，同样，可以设置url匹配模式，过滤器名称等。这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。
 * 除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包。
 * <p>
 * 可以看到，我们配置的两个过滤器都生效了。细心的读者会发现，第二个Filter我们并没有指定执行的顺序，但是却在第一个Filter之前执行。
 * 这里需要解释一下，@WebFilter这个注解并没有指定执行顺序的属性，其执行顺序依赖于Filter的名称，是根据Filter类名（注意不是配置的filter的名字）的字母顺序倒序排列，
 * 并且@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器。有兴趣的朋友可以自己实验一下。
 */
@WebFilter(urlPatterns = "/*", filterName = "logFilter2")
public class LogCostFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("LogFilter2 Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
