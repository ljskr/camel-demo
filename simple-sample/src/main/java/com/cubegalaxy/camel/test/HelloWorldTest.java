package com.cubegalaxy.camel.test;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorldTest {

    public static void main(String[] args) throws Exception {
//        HelloWorld.test1();
//        HelloWorldTest.test2();
        HelloWorldTest.test3();
    }

    /**
     * 只打印 hello world
     *
     * @throws Exception
     */
    public static void test1() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new HelloWorldRoute());
        context.start();
    }

    /**
     * 文件拷贝
     *
     * @throws Exception
     */
    public static void test2() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
//                from("file:test1").to("file:test2");
                from("file:test1?noop=true").to("file:test2");
            }
        });
        context.start();
//        while (true) {
//            Thread.sleep(2000);
//        }
        System.out.println("done");
    }


    /**
     * 订阅 mqtt 消息
     *
     * @throws Exception
     */
    public static void test3() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("paho:amq/topic?brokerUrl=tcp://192.168.3.3:1883&userName=rabbitmq&password=123456")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                byte[] message = (byte[]) exchange.getIn().getBody();
                                System.out.println("get message: " + new String(message));
                            }
                        });
            }
        });
        context.start();
        System.out.println("Start done");
    }
}
