package com.changjian.mqtttest.MqttClient.Entity;

import lombok.Data;

@Data
public class DetectRule {
    private String id;
    private String detection_item;
    private String point_sub_id;
    private String detection_key;
    private long detection_value;
    private long detection_no;
    private String image_id;
    private String detection_tag;
    private String point_id;
}
