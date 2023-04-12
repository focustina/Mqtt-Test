package com.changjian.mqtttest.MqttClient.Entity;

import lombok.Data;

@Data
public class RuleTrigger {
    private String id;
    private String scenes_id;
    private String scene_version;
    private String area_id;
    private long detection_no;
    private String detection_tag;
    private long warn;
    private String warn_row;
    private String warn_index;
    private String image_collect;
    private String image_collect_row;
    private long event_time;
}
