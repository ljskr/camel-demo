package com.cubegalaxy.camel.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestConsumerBean {

    private static final Logger log = LoggerFactory.getLogger(TestConsumerBean.class);

    public void showMessage(String msg) {
        log.info("[TestConsumerBean.showMessage] get message {}", msg);
    }

    public void showMessageByte(byte[] b) {
        String msg = new String(b);
        log.info("[TestConsumerBean.showMessageByte] get message {}", msg);
    }
}
