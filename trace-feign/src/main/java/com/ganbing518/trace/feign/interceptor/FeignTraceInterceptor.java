package com.ganbing518.trace.feign.interceptor;

import com.ganbing518.trace.common.log.constants.TraceConstants;
import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.common.log.util.TraceUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 16:09
 */
public class FeignTraceInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = TraceContext.getTraceId();
        if (StringUtils.isEmpty(traceId)) {
            traceId = TraceUtils.newTraceId();
        }
        requestTemplate.header(TraceConstants.X_COMMON_TRACE_ID, traceId);
    }
}
