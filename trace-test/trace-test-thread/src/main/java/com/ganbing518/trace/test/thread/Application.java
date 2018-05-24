package com.ganbing518.trace.test.thread;

import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.common.log.util.TraceUtils;
import com.ganbing518.trace.thread.executor.TraceThreadPoolTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 18:47
 */
public class Application {

    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        TraceThreadPoolTaskExecutor executor = new TraceThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("trace-thread-");
        executor.initialize();

        String traceId = TraceUtils.newTraceId();
        TraceContext.setTraceId(traceId);

        executor.execute(()->LOGGER.info(traceId));
        executor.submit(()->LOGGER.info(traceId));
        executor.shutdown();
    }
}
