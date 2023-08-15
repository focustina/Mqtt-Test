package com.changjian.mqtttest.MqttClient.Config;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MqttReceiveHandle {
    String ruletopic;
    String ruledatasource;
    Gson gson = new Gson();

    public void handle(Message<?> message) {
        log.info("收到订阅消息: {}",gson.toJson(message));
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
        String[] topicArr = topic.split("/");
        this.ruletopic  = topicArr[0];
        this.ruledatasource = topicArr[4];
        log.info("消息主题：{}", topic);
        String jsonMsg = String.valueOf((message.getPayload()));

        //log.info("发送的Packet数据{}", JSON.toJSONString(packet));

    }


    public String getRuletopic() {
        return ruletopic;
    }

    public String getRuledatasource() {
        return ruledatasource;
    }

}
