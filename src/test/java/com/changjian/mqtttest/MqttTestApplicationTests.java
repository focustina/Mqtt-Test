package com.changjian.mqtttest;

import com.changjian.mqtttest.MqttClient.MqttPushClient;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqttTestApplicationTests {

    @Test
    void contextLoads() {
        MqttPushClient mqttPushClient = new MqttPushClient();
        mqttPushClient.publish("test22","test343",1,true);






    }

}
