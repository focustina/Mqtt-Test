package com.changjian.mqtttest.MqttClient.dto;


/**
 * Create By Spring-2022/11/3
 */

public class MqttCmds {

    public MqttCmds(String cmdName, String cmdValue) {
        this.cmdName = cmdName;
        this.cmdValue = cmdValue;
    }

    private String cmdName;
    private String cmdValue;

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public String getCmdValue() {
        return cmdValue;
    }

    public void setCmdValue(String cmdValue) {
        this.cmdValue = cmdValue;
    }

    @Override
    public String toString() {
        return "MqttCmds{" +
                "cmdName='" + cmdName + '\'' +
                ", cmdValue='" + cmdValue + '\'' +
                '}';
    }
}

