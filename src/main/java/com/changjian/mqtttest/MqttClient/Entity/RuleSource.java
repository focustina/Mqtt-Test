package com.changjian.mqtttest.MqttClient.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("rulesource")
public class RuleSource {
    @TableField("id")
    private String id;
    @TableField("scenes_id")
    private String scenes_id;
    @TableField("scene_version")
    private String scene_version;
    @TableField("area_id")
    private String area_id;
    @TableField("point_id")
    private  String point_id;
    @TableField("detection_tag")
    private String detection_tag;
    @TableField("detection_no")
    private long  detection_no;
    @TableField("image_id")
    private String image_id;
    @TableField("event_time")
    private long event_time;


}
