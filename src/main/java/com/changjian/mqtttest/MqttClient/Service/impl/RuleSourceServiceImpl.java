package com.changjian.mqtttest.MqttClient.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changjian.mqtttest.MqttClient.Entity.RuleSource;
import com.changjian.mqtttest.MqttClient.Mapper.RuleSourceMapper;
import com.changjian.mqtttest.MqttClient.Service.RuleSourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class RuleSourceServiceImpl extends ServiceImpl<RuleSourceMapper,RuleSource> implements RuleSourceService{

}