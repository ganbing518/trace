package com.ganbing518.trace.test.web.controller;

import com.ganbing518.trace.common.log.context.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liyan on 2018.05.25.
 *
 * @author liyan
 *         DATE 2018.05.25.
 */
@Controller
@RequestMapping("filter")
public class DemoFilterController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DemoFilterController.class);

    @GetMapping("/demo")
    @ResponseBody
    public  String sayFilterHello(){
        String traceId = TraceContext.getTraceId();
        LOGGER.info(traceId);
        return traceId;
    }
}
