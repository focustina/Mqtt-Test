package com.changjian.mqtttest.MqttClient.Entity;

import lombok.Data;
@Data
public class RuleSource {
    private String id;
    private String scenes_id;
    private String scene_version;
    private String area_id;
    private  String point_id;
    private String detection_tag;
    private long  detection_no;
    private String image_id;
    private long event_time;


}
