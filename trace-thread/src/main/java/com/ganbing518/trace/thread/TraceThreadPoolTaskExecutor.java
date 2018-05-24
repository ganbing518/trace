package com.ganbing518.trace.thread;

import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import java.util.concurrent.*;

/**
 * Description:
 *
 * if want use @Async to do something,you can implements AsyncConfigurer and set Executor with TraceThreadPoolTaskExecutor
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 18:35
 */
public class TraceThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private int queueCapacity = Integer.MAX_VALUE;

    private boolean allowCoreThreadTimeOut = false;

    private TaskDecorator taskDecorator;

    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    protected ExecutorService initializeExecutor(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);

        ThreadPoolExecutor executor;
        if (this.taskDecorator != null) {
            executor = new TraceThreadPoolExecutor(
                    this.getCorePoolSize(), this.getMaxPoolSize(), this.getKeepAliveSeconds(), TimeUnit.SECONDS,
                    queue, threadFactory, rejectedExecutionHandler) {
                @Override
                public void execute(Runnable command) {
                    super.execute(taskDecorator.decorate(command));
                }
            };
        } else {
            executor = new TraceThreadPoolExecutor(
                    this.getCorePoolSize(), this.getMaxPoolSize(), this.getKeepAliveSeconds(), TimeUnit.SECONDS,
                    queue, threadFactory, rejectedExecutionHandler);

        }

        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;
        return executor;
    }

    @Override
    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    @Override
    public void setTaskDecorator(TaskDecorator taskDecorator) {
        this.taskDecorator = taskDecorator;
    }

    @Override
    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() {
        Assert.state(this.threadPoolExecutor != null, "ThreadPoolTaskExecutor not initialized");
        return this.threadPoolExecutor;
    }
}
