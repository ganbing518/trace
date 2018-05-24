/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ganbing518.trace.thread.factory;

import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.common.log.util.TraceUtils;
import com.ganbing518.trace.thread.TraceThread;
import com.ganbing518.trace.thread.util.WrapUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * InternalThreadFactory.
 */
public class NamedTraceThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_SEQ_NUM = new AtomicInteger(1);

    private final AtomicInteger threadNum = new AtomicInteger(1);

    private final String namePrefix;

    private final boolean daemon;

    private final ThreadGroup group;

    public NamedTraceThreadFactory() {
        this("pool-" + POOL_SEQ_NUM.getAndIncrement(), false);
    }

    public NamedTraceThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedTraceThreadFactory(String prefix, boolean daemon) {
        namePrefix = prefix + "-thread-";
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name = namePrefix + threadNum.getAndIncrement() + "#";
        String traceId = TraceContext.getTraceId();
        if (StringUtils.isEmpty(traceId)) {
            traceId = TraceUtils.newTraceId();
        }
        Thread t = new TraceThread(group, WrapUtils.wrapRunnable(runnable, TraceContext.getTraceId()), name, 0);
        t.setDaemon(daemon);
        return t;
    }


}