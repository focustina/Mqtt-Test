package com.changjian.mqtttest.MqttClient.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changjian.mqtttest.MqttClient.Entity.RuleHandle;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表rulehandle进行操作
 */
@Mapper
public interface RuleHandleMapper extends BaseMapper<RuleHandle> {
}
