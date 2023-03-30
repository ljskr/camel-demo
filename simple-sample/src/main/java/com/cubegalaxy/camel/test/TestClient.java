package com.cubegalaxy.camel.test;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClient {
    public static void main(String[] args) throws Exception {
        String broker = "tcp://192.168.3.3:1883";
        String username = "rabbitmq";
        String password = "123456";
        String clientid = "publish_client";
        String topic = "amq.topic";
        int qos = 0;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String content = sdf.format(now) + " 测试数据123";

        MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        client.connect(options);

        // 创建消息并设置 QoS
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        // 发布消息
        client.publish(topic, message);
        System.out.println("Message published");
        System.out.println("topic: " + topic);
        System.out.println("Qos: " + qos);
        System.out.println("message content: " + content);
        // 关闭连接
        client.disconnect();
        // 关闭客户端
        client.close();

    }
}
