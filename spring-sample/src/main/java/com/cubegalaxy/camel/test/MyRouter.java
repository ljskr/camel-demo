package com.cubegalaxy.camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
class MyRouter extends RouteBuilder {
    @Override
    public void configure() {
        from("paho:amq/topic?brokerUrl=tcp://192.168.3.3:1883&userName=rabbitmq&password=123456")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        byte[] message = (byte[]) exchange.getIn().getBody();
                        System.out.println("[MyRouter] get message: " + new String(message));
                    }
                });
    }
}