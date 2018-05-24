package com.ganbing518.trace.test.provider.impl;

import com.ganbing518.trace.test.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 16:41
 */
public class DemoServiceImpl implements DemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public void sayHello(String hello) {
        LOGGER.info("===================================================");
        LOGGER.info(hello);
        LOGGER.info("===================================================");
    }
}
