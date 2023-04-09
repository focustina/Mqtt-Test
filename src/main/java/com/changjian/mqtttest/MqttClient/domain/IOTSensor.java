package com.changjian.mqtttest.MqttClient.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_sensor
 */
@TableName(value = "tb_sensor")
public class IOTSensor implements Serializable {

    public IOTSensor(){
    }

    public IOTSensor(Integer deviceId, String sensorTag, Object sensorVal) {
        this(deviceId,sensorTag,sensorVal,"");
    }

    public IOTSensor(Integer deviceId, String sensorTag, Object sensorVal, String sensorName) {
        this.deviceId = deviceId;
        this.sensorTag = sensorTag;
        this.sensorVal = sensorVal;
        this.sensorName = sensorName;
    }

    /**
     *
     */
    @TableField(value = "device_id")
    private Integer deviceId;

    /**
     *
     */
    @TableField(value = "sensor_tag")
    private String sensorTag;

    /**
     *
     */
    @TableField(value = "sensor_name")
    private String sensorName;

    /**
     *
     */
    @TableField(value = "sensor_val")
    private Object sensorVal;

    /**
     *
     */
    @TableField(value = "record_time")
    private Date recordTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     *
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     *
     */
    public String getSensorTag() {
        return sensorTag;
    }

    /**
     *
     */
    public void setSensorTag(String sensorTag) {
        this.sensorTag = sensorTag;
    }

    /**
     *
     */
    public String getSensorName() {
        return sensorName;
    }

    /**
     *
     */
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    /**
     *
     */
    public Object getSensorVal() {
        return sensorVal;
    }

    /**
     *
     */
    public void setSensorVal(Object sensorVal) {
        this.sensorVal = sensorVal;
    }

    /**
     *
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     *
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        IOTSensor other;
        other = (IOTSensor) that;
        return (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
                && (this.getSensorTag() == null ? other.getSensorTag() == null : this.getSensorTag().equals(other.getSensorTag()))
                && (this.getSensorName() == null ? other.getSensorName() == null : this.getSensorName().equals(other.getSensorName()))
                && (this.getSensorVal() == null ? other.getSensorVal() == null : this.getSensorVal().equals(other.getSensorVal()))
                && (this.getRecordTime() == null ? other.getRecordTime() == null : this.getRecordTime().equals(other.getRecordTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getSensorTag() == null) ? 0 : getSensorTag().hashCode());
        result = prime * result + ((getSensorName() == null) ? 0 : getSensorName().hashCode());
        result = prime * result + ((getSensorVal() == null) ? 0 : getSensorVal().hashCode());
        result = prime * result + ((getRecordTime() == null) ? 0 : getRecordTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceId=").append(deviceId);
        sb.append(", sensorTag=").append(sensorTag);
        sb.append(", sensorName=").append(sensorName);
        sb.append(", sensorVal=").append(sensorVal);
        sb.append(", recordTime=").append(recordTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
