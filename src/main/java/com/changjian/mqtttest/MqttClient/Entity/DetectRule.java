package com.changjian.mqtttest.MqttClient.Entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("detectrule")
public class DetectRule {
    @TableField("id")
    private String id;
    @TableField("detection_item")
    private String detection_item;
    @TableField("point_sub_id")
    private String point_sub_id;
    @TableField("detection_key")
    private String detection_key;
    @TableField("detection_value")
    private long detection_value;
    @TableField("detection_no")
    private long detection_no;
    @TableField("image_id")
    private String image_id;
    @TableField("detection_tag")
    private String detection_tag;
    @TableField("point_id")
    private String point_id;
}
