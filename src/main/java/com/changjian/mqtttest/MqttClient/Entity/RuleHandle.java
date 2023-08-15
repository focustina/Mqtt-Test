package com.changjian.mqtttest.MqttClient.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("rulehandle")
public class RuleHandle {
    @TableField("id")
    private String  id;
    @TableField("scenes_id")
    private String scenes_id;
    @TableField("scene_version")
    private String scene_version;
    @TableField("area_id")
    private String area_id;
    @TableField("detection_no")
    private long detection_no;
    @TableField("detection_tag")
    private String detection_tag;
    @TableField("warn")
    private long warn;
    @TableField("point_collect")
    private String point_collect;
    @TableField("image_collect")
    private String image_collect;
    @TableField("event_time")
    private long event_time;



}
