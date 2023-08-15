package com.changjian.mqtttest.MqttClient.Controller;

import com.changjian.mqtttest.MqttClient.Entity.RuleWarn;
import com.changjian.mqtttest.MqttClient.Mapper.RuleWarnMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/rulewarn")
public class RuleWarnController {
    @Autowired
    RuleWarnMapper mapper;
    @PostMapping
    public Boolean saverulewarn(@RequestBody RuleWarn rulewarn){
        log.warn("rulewarn {}",rulewarn);
        int i = mapper.insert(rulewarn);
        return i == 1;
    }

}
