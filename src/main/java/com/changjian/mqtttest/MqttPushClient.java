package com.changjian.mqtttest;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.*;
import lombok.extern.slf4j.Slf4j;


/**
 * 创建一个MQTT客户端
 *
 */
@Slf4j
public class MqttPushClient {

    public static String MQTT_HOST = "";
    public static String MQTT_CLIENTID = "";
    public static String MQTT_USERNAME = "";
    public static String MQTT_PASSWORD = "";
    public static int MQTT_TIMEOUT = 10;
    public static int MQTT_KEEPALIVE = 10;

    private MqttClient client;
    private static volatile MqttPushClient mqttClient = null;

    public static MqttPushClient getInstance() {
        if (mqttClient == null) {
            synchronized (MqttPushClient.class) {
                if (mqttClient == null) {
                    mqttClient = new MqttPushClient();
                }
            }
        }
        return mqttClient;
    }

    private MqttPushClient() {
        log.info("Connect MQTT: " + this);
        connect();
    }

    /**
     * 创建连接
     */
    private void connect() {
        try {
            client = new MqttClient(MQTT_HOST, MQTT_CLIENTID, new MemoryPersistence());
            MqttConnectOptions option = new MqttConnectOptions();
            option.setCleanSession(true);//设置是否清空session,false表示服务器会保留客户端的连接记录，true表示每次连接到服务器都以新的身份连接
            option.setUserName(MQTT_USERNAME);//设置连接的用户名
            option.setPassword(MQTT_PASSWORD.toCharArray());//设置连接的密码
            option.setConnectionTimeout(MQTT_TIMEOUT);// 设置超时时间
            option.setKeepAliveInterval(MQTT_KEEPALIVE);// 设置会话心跳时间
            option.setAutomaticReconnect(true);// 自动重连
            try {
                client.setCallback(new MqttPushCallback());
                client.connect(option);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布主题，用于通知<br>
     * 默认qos为1 非持久化
     *
     * @param topic
     * @param data
     */
    public void publish(String topic, String data) {
        publish(topic, data, 1, false);
    }

    /**
     * 发布
     *
     * @param topic
     * @param data
     * @param qos
     * @param retained
     */
    public void publish(String topic, String data, int qos, boolean retained) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);//设置qos，决定消息到达次数。
        message.setRetained(retained);// 服务器是否保存消息
        message.setPayload(data.getBytes());//设置消息内容
        MqttTopic mqttTopic = client.getTopic(topic);// 设置消息主题
        if (null == mqttTopic) {
            log.error("Topic Not Exist");
        }
        MqttDeliveryToken token;
        try {
            token = mqttTopic.publish(message);
            token.waitForCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题 qos默认为1
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 1);
    }

    /**
     * 订阅某个主题
     *
     * @param topic
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            client.subscribe(topic, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
