package com.changjian.mqtttest.MqttClient.Controller;

import com.changjian.mqtttest.MqttClient.Entity.RuleAggregation;
import com.changjian.mqtttest.MqttClient.Mapper.RuleAggregationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/ruleaggregation")
public class RuleAggregationController {
    @Autowired
    RuleAggregationMapper mapper;
    @PostMapping
    public Boolean saveruleaggregation(@RequestBody RuleAggregation ruleaggregation){
        log.warn("ruleaggregation {}",ruleaggregation);
        int i = mapper.insert(ruleaggregation);
        return i == 1;
    }


}
