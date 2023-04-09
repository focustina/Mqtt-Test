package com.changjian.mqtttest.MqttClient;

import cn.hutool.core.util.RandomUtil;
import com.changjian.mqtttest.MqttClient.Detect.DetectionResult;
import com.changjian.mqtttest.MqttClient.Detect.DetectionRule;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.google.gson.Gson;
import java.util.*;


public class Simulator {
    private static final String BROKER_URL = "tcp://192.168.100.213:1883";
    private static final String TOPIC = "edgex/rule002-1";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "public";


    public static void main(String[] args) {
        String jsonstr;
        String clientId = "test_emqx111";
        int count = 0;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient mqttClient = new MqttClient(BROKER_URL, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(USERNAME);
            connOpts.setPassword(PASSWORD.toCharArray());
            mqttClient.connect(connOpts);
            System.out.println("Connected to broker: " + BROKER_URL);

            while (true) {
                long time =  Calendar.getInstance().getTimeInMillis() / 1000;
                String t = RandomUtil.randomNumbers(10);
                DetectionResult detectionResult = new DetectionResult();
                List<DetectionRule> list = new ArrayList<DetectionRule>();
//                DetectionRule detectionRule1 = new DetectionRule("detection-item-1","point-1-detection-item-1","device_lamp_red",2);
//                DetectionRule detectionRule2 = new DetectionRule("detection-item-2","point-1-detection-item-1","device_lamp_yellow",2);
//                DetectionRule detectionRule3 = new DetectionRule("detection-item-3","point-1-detection-item-2","personnel",2);
//                DetectionRule detectionRule4 = new DetectionRule("detection-item-4","point-1-detection-item-3","kettle",2);
//                DetectionRule detectionRule5 = new DetectionRule("detection-item-4","point-1-detection-item-2","device_lamp",2);
//                detectionResult.setId(UUID.randomUUID().toString());
//                detectionResult.setScenes_id("9e34aebb-0446-4d13-a91f-2ff2871d504b");
//                detectionResult.setArea_id("9e27aebb-0446-4d13-a91f-2ff2871d504b");
//                detectionResult.setScene_version("v1.0.3");
//                detectionResult.setPoint_id("1");
//                detectionResult.setImage_id(t);
//                detectionResult.setDetection_tag(UUID.randomUUID().toString());
//                detectionResult.setDetection_no((int)(Math.random()*10+1));
//                detectionResult.setEvent_time(time);
//                list.add(detectionRule1);
//                list.add(detectionRule2);
//                list.add(detectionRule3);
//                list.add(detectionRule4);
//                detectionResult.setDetection_rule(list);


                Gson gson = new Gson();
                String payload = gson.toJson(detectionResult);
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(0);
                mqttClient.publish(TOPIC, message);
                System.out.println("Published message: " + payload);
                Thread.sleep(4000); // 4秒后再次发布
                count = count +1;
                System.out.println("这是发布的第" +count + "条消息");
                System.out.println("test");
                System.out.println("push");
                System.out.println("这是一个输出test");


            }

        } catch (MqttException me) {
            System.out.println("Reason: " + me.getReasonCode());
            System.out.println("Message: " + me.getMessage());
            System.out.println("Loc: " + me.getLocalizedMessage());
            System.out.println("Cause: " + me.getCause());
            System.out.println("Exception: " + me);
            me.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


