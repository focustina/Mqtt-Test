package com.changjian.mqtttest.MqttClient.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("rulewarn")
public class RuleWarn {
    @TableField("id")
    private String id;
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
    @TableField("warn_row")
    private String warn_row;
    @TableField("warn_index")
    private String warn_index;
    @TableField("image_id")
    private String image_id;
    @TableField("image_collect_row")
    private String image_collect_row;
    @TableField("event_time")
    private long event_time;
}