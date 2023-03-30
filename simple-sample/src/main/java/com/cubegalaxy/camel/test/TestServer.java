package com.cubegalaxy.camel.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TestServer {
    public static void main(String[] args) throws Exception {
        String broker = "tcp://192.168.3.3:1883";
        String username = "rabbitmq";
        String password = "123456";
        String clientid = "subscribe_client";
        String topic = "amq.topic";
        int qos = 0;

        MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        // 设置回调
        client.setCallback(new MqttCallback() {

            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost: " + cause.getMessage());
            }

            public void messageArrived(String topic, MqttMessage message) {
                System.out.println("Message received");
                System.out.println("topic: " + topic);
                System.out.println("Qos: " + message.getQos());
                System.out.println("message content: " + new String(message.getPayload()));
                System.out.println();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
            }
        });

        client.connect(options);
        client.subscribe(topic, qos);
    }
}
