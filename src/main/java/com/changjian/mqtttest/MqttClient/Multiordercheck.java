package com.changjian.mqtttest.MqttClient;import cn.hutool.core.util.RandomUtil;
import com.changjian.mqtttest.MqttClient.Detect.DetectionResult;
import com.changjian.mqtttest.MqttClient.Detect.DetectionRule;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;


public class Multiordercheck {
    private static final String BROKER_URL = "tcp://192.168.13.100:32754";
    private static final String TOPIC = "edgex/rule/device/onvif-camera/abnormalSingle/ruleSource/1688722027938508801";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "public";


    public static void main(String[] args) {
        String jsonstr;
        String clientId = "test_emqx111";
        int count = 0;
        int countsum = 2;
        MemoryPersistence persistence = new MemoryPersistence();


        try {
            MqttClient mqttClient = new MqttClient(BROKER_URL, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(USERNAME);
            connOpts.setPassword(PASSWORD.toCharArray());
            mqttClient.connect(connOpts);
            System.out.println("Connected to broker: " + BROKER_URL);

            while (true) {
                DateTime time =new DateTime();
                long tt =time.getMillis() ;
                String t = RandomUtil.randomNumbers(10);
                DetectionResult detectionResult = new DetectionResult();
                detectionResult.setId(UUID.randomUUID().toString());
                detectionResult.setScenes_id("1688722027938508801");
                detectionResult.setArea_id("1661192638797189121");
                detectionResult.setScene_version("5");
                detectionResult.setImage_id(t);
                detectionResult.setDetection_tag(UUID.randomUUID().toString());
                detectionResult.setEvent_time(tt);

                for(int i=1;i<countsum;i++){
                    detectionResult.setPoint_id("1669992849661050881");
                    List<DetectionRule> list = new ArrayList<DetectionRule>();
                    // long time =  Calendar.getInstance().getTimeInMillis() ;
                    DetectionRule detectionRule1 = new DetectionRule("playphone101","1691016054863761410","person",1);
                    DetectionRule detectionRule2 = new DetectionRule("playphone102","1691016170387476482","person",0);
                    detectionResult.setDetection_no(1);
                    DetectionRule detectionRule3 = new DetectionRule("playphone103","1691016365749768193","person",2);
                    DetectionRule detectionRule4 = new DetectionRule("playphone104","1691261952155914241","person",2);
                //    DetectionRule detectionRule5 = new DetectionRule("detection-item-4","point-1-detection-item-2","device_lamp",2);
                    list.add(detectionRule1);
                    list.add(detectionRule2);
                    list.add(detectionRule3);
                    list.add(detectionRule4);
                    detectionResult.setDetection_rule(list);
                    Gson gson = new Gson();
                    String payload = gson.toJson(detectionResult);
                    MqttMessage message = new MqttMessage(payload.getBytes());
                    message.setQos(0);
                    mqttClient.publish(TOPIC, message);
                    System.out.println("Published message: " + payload);
                    count = count +1;
                    System.out.println("这是发布的第" +count + "条消息"  );
                    //                System.out.println("test");
                    //                System.out.println("push");
                    //                System.out.println("这是一个输出test");
//                    Thread.sleep(300);// 300毫秒后再次发布
                }
                for(int i=0;i<countsum;i++){
                    detectionResult.setPoint_id("1648632806831374337");
                    // long time =  Calendar.getInstance().getTimeInMillis() ;
                    List<DetectionRule> list = new ArrayList<DetectionRule>();
                    DetectionRule detectionRule1 = new DetectionRule("object2","1649226132572721154","穿工服",1);
//                    DetectionRule detectionRule2 = new DetectionRule("masks","1648854941190688770","不戴口罩",0);
                    detectionResult.setDetection_no(1);
//                    DetectionRule detectionRule3 = new DetectionRule("detection-item-3","point-1-detection-item-2","personnel",2);
//                    DetectionRule detectionRule4 = new DetectionRule("detection-item-4","point-1-detection-item-3","kettle",2);
//                    DetectionRule detectionRule5 = new DetectionRule("detection-item-4","point-1-detection-item-2","device_lamp",2);
                    list.add(detectionRule1);
//                    list.add(detectionRule2);
//                    list.add(detectionRule3);
//                    list.add(detectionRule4);
                    detectionResult.setDetection_rule(list);
                    Gson gson = new Gson();
                    String payload = gson.toJson(detectionResult);
                    MqttMessage message = new MqttMessage(payload.getBytes());
                    message.setQos(0);
                    mqttClient.publish(TOPIC, message);
                    System.out.println("Published message: " + payload);
                    count = count +1;
                    System.out.println("这是发布的第" +count + "条消息"  );
                    //                System.out.println("test");
                    //                System.out.println("push");
                    //                System.out.println("这是一个输出test");
//                    Thread.sleep(300);// 300毫秒后再次发布
                }

                Thread.sleep(1000);

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


