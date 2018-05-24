package com.ganbing518.trace.test.dubbo.consumer;

import com.ganbing518.trace.common.log.context.TraceContext;
import com.ganbing518.trace.common.log.util.TraceUtils;
import com.ganbing518.trace.test.dubbo.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 17:37
 */
public class Consumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
        context.start();
        DemoService demoService = context.getBean(DemoService.class);
        String traceId = TraceUtils.newTraceId();
        TraceContext.setTraceId(traceId);
        LOGGER.info("===================================================");
        LOGGER.info(traceId);
        LOGGER.info("===================================================");
        demoService.sayHello("world");
    }
}
