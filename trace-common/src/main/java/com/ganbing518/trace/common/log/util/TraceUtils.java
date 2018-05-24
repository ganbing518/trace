package com.ganbing518.trace.common.log.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 14:22
 */
public class TraceUtils {

    /**
     * 获取新的traceId
     * @return
     */
    public static String newTraceId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return String.format("%sT%06d", sdf.format(new Date()), ThreadLocalRandom.current().nextInt(100000));
    }
}
