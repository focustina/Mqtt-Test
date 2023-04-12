package com.changjian.mqtttest.MqttClient.Detect;

import java.util.List;

public class DetectionResult {
    private String id;
    private String scene_version;
    private String scenes_id;

    private String area_id;
    private String point_id;
    private List<DetectionRule> detection_rule;
    private String detection_tag;
    private int detection_no;
    private String image_id;
    private long event_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScenes_id() {
        return scenes_id;
    }

    public void setScenes_id(String scenes_id) {
        this.scenes_id = scenes_id;
    }

    public String getScene_version() {
        return scene_version;
    }

    public void setScene_version(String scene_version) {
        this.scene_version = scene_version;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getPoint_id() {
        return point_id;
    }

    public void setPoint_id(String point_id) {
        this.point_id = point_id;
    }

    public List<DetectionRule> getDetection_rule() {
        return detection_rule;
    }

    public void setDetection_rule(List<DetectionRule> detection_rule) {
        this.detection_rule = detection_rule;
    }

    public String getDetection_tag() {
        return detection_tag;
    }

    public void setDetection_tag(String detection_tag) {
        this.detection_tag = detection_tag;
    }

    public int getDetection_no() {
        return detection_no;
    }

    public void setDetection_no(int detection_no) {
        this.detection_no = detection_no;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public long getEvent_time() {
        return event_time;
    }

    public void setEvent_time(long event_time) {
        this.event_time = event_time;
    }

    // getters and setters

}
