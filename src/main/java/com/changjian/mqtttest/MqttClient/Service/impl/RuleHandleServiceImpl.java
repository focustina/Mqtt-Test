package com.changjian.mqtttest.MqttClient.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changjian.mqtttest.MqttClient.Entity.DetectRule;
import com.changjian.mqtttest.MqttClient.Entity.RuleHandle;
import com.changjian.mqtttest.MqttClient.Mapper.DetectRuleMapper;
import com.changjian.mqtttest.MqttClient.Mapper.RuleHandleMapper;
import com.changjian.mqtttest.MqttClient.Service.DetectRuleService;
import com.changjian.mqtttest.MqttClient.Service.RuleHandleService;
import com.changjian.mqtttest.MqttClient.Service.RuleSourceService;

public class RuleHandleServiceImpl extends ServiceImpl<RuleHandleMapper, RuleHandle> implements RuleHandleService {
}
