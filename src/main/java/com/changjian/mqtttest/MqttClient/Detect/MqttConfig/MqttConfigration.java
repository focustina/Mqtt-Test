package com.changjian.mqtttest.MqttClient.Detect.MqttConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MQTT 客户端连接的基本配置，配置信息见 application.yml
 * Create By Spring-2022/10/29
 */
@Configuration
@ConfigurationProperties(prefix = "com.mqtt")
public class MqttConfigration {
    private String url;
    private String clientId;
    private String receiveTopics;
    private String sendTopics;
    private String username;
    private String password;
    private String timeout;
    private String keepalive;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReceiveTopics() {
        return receiveTopics;
    }

    public void setReceiveTopics(String topics) {
        this.receiveTopics = topics;
    }

    public String getSendTopics() {
        return sendTopics;
    }

    public void setSendTopics(String sendTopics) {
        this.sendTopics = sendTopics;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(String keepalive) {
        this.keepalive = keepalive;
    }
}