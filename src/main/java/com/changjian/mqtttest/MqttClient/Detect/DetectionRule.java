package com.changjian.mqtttest.MqttClient.Detect;

public class DetectionRule {
    private String detection_item;
    private String point_sub_id;
    private String detection_key;
    private int detection_value;

    public DetectionRule(String detection_item, String point_sub_id, String detection_key, int detection_value) {
        this.detection_item = detection_item;
        this.point_sub_id = point_sub_id;
        this.detection_key = detection_key;
        this.detection_value = detection_value;
    }

    public void setDetection_item(String detection_item) {
        this.detection_item = detection_item;
    }

    public String getPoint_sub_id() {
        return point_sub_id;
    }

    public void setPoint_sub_id(String point_sub_id) {
        this.point_sub_id = point_sub_id;
    }

    public String getDetection_key() {
        return detection_key;
    }

    public void setDetection_key(String detection_key) {
        this.detection_key = detection_key;
    }

    public int getDetection_value() {
        return detection_value;
    }

    public void setDetection_value(int detection_value) {
        this.detection_value = detection_value;
    }


    // getters and setters

}
