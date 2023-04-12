package com.changjian.mqtttest.MqttClient.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changjian.mqtttest.MqttClient.Entity.DetectRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表detectrule进行操作
 */
@Mapper
public interface DetectRuleMapper extends BaseMapper<DetectRule> {
}
