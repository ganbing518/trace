package com.ganbing518.trace.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ganbing518.trace.common.log.constants.TraceConstants;
import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.common.log.util.TraceUtils;
import org.springframework.util.StringUtils;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-04 11:34
 */
public class ServletTraceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String traceId = request.getHeader(TraceConstants.X_COMMON_TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = response.getHeader(TraceConstants.X_COMMON_TRACE_ID);
            if (StringUtils.isEmpty(traceId)) {
                traceId = TraceUtils.newTraceId();
            }
        }

        response.setHeader(TraceConstants.X_COMMON_TRACE_ID, traceId);
        TraceContext.setTraceId(traceId);
        try {
            chain.doFilter(request, response);
        } finally {
            TraceContext.clear();
        }
    }

    @Override
    public void destroy() {

    }
}
