package com.ganbing518.trace.web.interceptor;

import com.ganbing518.trace.common.log.constants.TraceConstants;
import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.common.log.util.TraceUtils;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 15:53
 */
public class WebTraceInterceptor extends HandlerInterceptorAdapter implements Ordered {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(TraceConstants.X_COMMON_TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = response.getHeader(TraceConstants.X_COMMON_TRACE_ID);
            if (StringUtils.isEmpty(traceId)) {
                traceId = TraceUtils.newTraceId();
            }
        }
        response.setHeader(TraceConstants.X_COMMON_TRACE_ID, traceId);
        TraceContext.setTraceId(traceId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String traceId = request.getHeader(TraceConstants.X_COMMON_TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = TraceContext.getTraceId();
            if (StringUtils.isEmpty(traceId)) {
                traceId = TraceUtils.newTraceId();
            }
        }
        response.setHeader(TraceConstants.X_COMMON_TRACE_ID, traceId);
        TraceContext.clear();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
