package com.ganbing518.trace.test.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 16:41
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-provider.xml"});
        context.start();

        System.in.read(); // press any key to exit
    }
}
