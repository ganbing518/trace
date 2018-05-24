package com.ganbing518.trace.thread.executor;

import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.thread.util.WrapUtils;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 18:34
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new FutureTask<T>(runnable, value){
            final String traceId = TraceContext.getTraceId();
            @Override
            public void run() {
                try {
                    TraceContext.setTraceId(traceId);
                    super.run();
                } finally {
                    TraceContext.clear();
                }
            }
        };
    }

    @Override
    public void execute(Runnable command) {
        super.execute(WrapUtils.wrapRunnable(command, TraceContext.getTraceId()));
    }



    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable){
            final String traceId = TraceContext.getTraceId();
            @Override
            public void run() {
                try {
                    TraceContext.setTraceId(traceId);
                    super.run();
                } finally {
                    TraceContext.clear();
                }
            }
        };
    }
}
