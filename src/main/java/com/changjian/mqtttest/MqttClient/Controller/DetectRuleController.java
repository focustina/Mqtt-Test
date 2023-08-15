package com.changjian.mqtttest.MqttClient.Controller;

import com.alibaba.fastjson.JSONObject;
import com.changjian.mqtttest.MqttClient.Config.MqttReceiveHandle;
import com.changjian.mqtttest.MqttClient.Detect.DetectionResult;
import com.changjian.mqtttest.MqttClient.Detect.DetectionRule;
import com.changjian.mqtttest.MqttClient.Entity.DetectRule;
import com.changjian.mqtttest.MqttClient.Entity.RuleSource;
import com.changjian.mqtttest.MqttClient.Mapper.DetectRuleMapper;
import com.changjian.mqtttest.MqttClient.Mapper.RuleSourceMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库插入数据
 */
@Slf4j
@RestController
@RequestMapping("/detectrule")
public class DetectRuleController {
    private MqttReceiveHandle mqttReceiveHandle;
    private RuleSource ruleSource;
    private DetectRule detectRule;
    private RuleSourceMapper sourceMapper;
    private DetectRuleMapper detectRuleMapper;
    Gson gson = new Gson();

    @Autowired
    DetectRuleMapper mapper;
    public void handle(Message<?> message){
        log.info("收到订阅消息: {}",gson.toJson(message));
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
        String[] topicArr = topic.split("/");
        String ruletopic  = topicArr[0];
        String ruledatasource = topicArr[4];
        log.info("消息主题：{}", topic);
        String jsonMsg = String.valueOf((message.getPayload()));
        if (ruledatasource == "ruleSource"){
            DetectionResult  detectionResult = JSONObject.parseObject(jsonMsg, DetectionResult.class);
            log.info("response:",detectionResult);
            String ruledectMsg = String.valueOf((detectionResult.getDetection_rule()));
            for(int i=0;i<ruledectMsg.length();i++){
                DetectionRule detectionRule =JSONObject.parseObject(ruledectMsg,DetectionRule.class);
                detectRule.setDetection_no(detectionResult.getDetection_no());
                detectRule.setDetection_tag(detectionResult.getDetection_tag());
                detectRule.setImage_id(detectionResult.getImage_id());
                detectRule.setPoint_id(detectionResult.getPoint_id());
                detectRule.setPoint_sub_id(detectionRule.getPoint_sub_id());
                detectRule.setDetection_item(detectionRule.getDetection_item());
                detectRule.setDetection_key(detectionRule.getDetection_key());
                detectRule.setDetection_value(detectionRule.getDetection_value());
                detectRuleMapper.insert(detectRule);

            }

        }

    }
//    @PostMapping
//    public Boolean saveDetectRule(@RequestBody DetectRule detectRule){
//
//        log.warn("detectRule {}",detectRule);
//        int i = mapper.insert(detectRule);
//        return i == 1;
//    }
}
