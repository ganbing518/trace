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
 * @date 2018-05-24 10:45
 */
@Activate(group = Constants.CONSUMER, order = -10001)
public class ConsumerTraceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String traceId = TraceContext.getTraceId();
        if (StringUtils.isBlank(traceId)) {
            traceId = TraceUtils.newTraceId();
        }

        RpcInvocation rpcInvocation = (RpcInvocation) invocation;
        rpcInvocation.setAttachment(TraceConstants.X_COMMON_TRACE_ID, traceId);

        try {
            return invoker.invoke(invocation);
        } finally {
            TraceContext.clear();
        }
    }
}
