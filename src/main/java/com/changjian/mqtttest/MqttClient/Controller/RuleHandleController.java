package com.changjian.mqtttest.MqttClient.Controller;

import com.changjian.mqtttest.MqttClient.Entity.RuleHandle;
import com.changjian.mqtttest.MqttClient.Mapper.RuleHandleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/rulehandle")
public class RuleHandleController {
    @Autowired
    RuleHandleMapper mapper;
    @PostMapping
    public Boolean saverulehandle(@RequestBody RuleHandle rulehandle){
        log.warn("rulehandle {}",rulehandle);
        int i = mapper.insert(rulehandle);
        return i == 1;

    }
}
