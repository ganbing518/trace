package com.ganbing518.trace.thread.util;

import com.ganbing518.trace.common.log.context.TraceContext;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 20:00
 */
public class WrapUtils {

    public static Runnable wrapRunnable(Runnable runnable, final String traceId){
        return  new Runnable() {
            @Override
            public void run() {
                TraceContext.setTraceId(traceId);
                try {
                    runnable.run();
                }finally {
                    TraceContext.clear();
                }
            }
        };
    }
}
