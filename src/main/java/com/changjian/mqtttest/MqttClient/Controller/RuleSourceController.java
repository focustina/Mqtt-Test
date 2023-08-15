package com.changjian.mqtttest.MqttClient.Controller;
import com.alibaba.fastjson.JSONObject;
import com.changjian.mqtttest.MqttClient.Detect.DetectionResult;
import com.changjian.mqtttest.MqttClient.Entity.RuleSource;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.changjian.mqtttest.MqttClient.Mapper.RuleSourceMapper;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/rulesource")
public class RuleSourceController {
    private RuleSource ruleSource;
    private RuleSourceMapper sourceMapper;
    Gson gson = new Gson();
    @Autowired
     RuleSourceMapper mapper;
    public void handle(Message<?> message){
        log.info("收到订阅消息: {}",gson.toJson(message));
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
        String[] topicArr = topic.split("/");
        String ruletopic  = topicArr[0];
        String ruledatasource = topicArr[4];
        log.info("消息主题：{}", topic);
        String jsonMsg = String.valueOf((message.getPayload()));
        DetectionResult detectionResult = JSONObject.parseObject(jsonMsg,DetectionResult.class);

        if (ruledatasource == "ruleSource"){
            log.info("response:",detectionResult);
            ruleSource.setId(detectionResult.getId());
            ruleSource.setDetection_no(detectionResult.getDetection_no());
            ruleSource.setDetection_tag(detectionResult.getDetection_tag());
            ruleSource.setArea_id(detectionResult.getArea_id());
            ruleSource.setImage_id(detectionResult.getImage_id());
            ruleSource.setPoint_id(detectionResult.getPoint_id());
            ruleSource.setScene_version(detectionResult.getScene_version());
            ruleSource.setScenes_id(detectionResult.getScenes_id());
            ruleSource.setEvent_time(detectionResult.getEvent_time());
            sourceMapper.insert(ruleSource);


        }




    }
    @PostMapping
    public Boolean saveRuleSource(@RequestBody RuleSource rulesource){
        log.warn("rulesource {}",rulesource);
        int  i = mapper.insert(rulesource);
        return i == 1;
    }
}




