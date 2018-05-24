package com.ganbing518.trace.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;
import com.ganbing518.trace.common.log.constants.TraceConstants;
import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.common.log.util.TraceUtils;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 10:39
 */
@Activate(group = Constants.PROVIDER, order = -10001)
public class ProviderTraceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String traceId = invocation.getAttachment(TraceConstants.X_COMMON_TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = TraceUtils.newTraceId();
        }

        TraceContext.setTraceId(traceId);

        try {
            return invoker.invoke(invocation);
        } finally {
            TraceContext.clear();
        }
    }
}
