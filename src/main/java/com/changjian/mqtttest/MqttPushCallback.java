package com.changjian.mqtttest;

import org.eclipse.paho.client.mqttv3.MqttCallback;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * MQTT 推送回调
 */
public class MqttPushCallback implements MqttCallback {

    private static final Logger log = LoggerFactory.getLogger(MqttPushCallback.class);

    //连接断开时的回调
    @Override
    public void connectionLost(Throwable cause) {
        log.info("断开连接，建议重连" + this);
        //断开连接，建议重连
    }

    //消息发送成功时的回调
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //log.info(token.isComplete() + "");
    }

    //收到下推消息时的回调
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("Topic: " + topic);
        log.info("Message: " + new String(message.getPayload()));

    }
}