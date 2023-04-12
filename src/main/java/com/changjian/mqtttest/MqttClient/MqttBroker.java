package com.changjian.mqtttest.MqttClient;

import cn.hutool.json.JSONUtil;
import com.changjian.mqtttest.MqttClient.Detect.DetectionResult;
import com.changjian.mqtttest.MqttClient.Detect.DetectionRule;
import com.changjian.mqtttest.MqttClient.Detect.MqttConfig.MqttConfigration;
import com.changjian.mqtttest.MqttClient.Entity.*;
import com.changjian.mqtttest.MqttClient.Service.RuleSourceService;
import com.changjian.mqtttest.MqttClient.Service.DetectRuleService;
import com.changjian.mqtttest.MqttClient.Service.RuleHandleService;
import com.changjian.mqtttest.MqttClient.Service.RuleAggregationService;
import com.changjian.mqtttest.MqttClient.Service.RuleWarnService;
import com.changjian.mqtttest.MqttClient.Service.RuleTriggerService;
import com.changjian.mqtttest.MqttClient.dto.MqttCmds;
import com.mysql.cj.xdevapi.AddResult;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.*;
import com.changjian.mqtttest.MqttClient.Detect.MqttConfig.MqttConfigration;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@MapperScan("com.changjian.mqtttest.MqttClient.Mapper")
@SpringBootApplication
public class MqttBroker {
    private static Logger LOGGER = LoggerFactory.getLogger(MqttBroker.class);

    private MqttConfigration mqttProperties;
    private RuleSourceService ruleSourceService;
    private RuleHandleService ruleHandleService;
    private RuleAggregationService ruleAggregationService;
    private RuleWarnService ruleWarnService;
    private RuleTriggerService ruleTriggerService;
    private DetectRuleService detectRuleService;



    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MqttPahoClientFactory mqttInClient() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        String[] mqttServerUrls = mqttProperties.getUrl().split(",");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(mqttServerUrls);

        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        options.setKeepAliveInterval(2);

        //接受离线消息
        options.setCleanSession(false);
        factory.setConnectionOptions(options);

        return factory;
    }

    //  配置Client，监听Topic
    //  如果我要配置多个client，应该怎么处理呢？这个也简单, 模仿此方法，多写几个client
    @Bean
    public MessageProducer inbound() {
        String[] inboundTopics = mqttProperties.getReceiveTopics().split(",");
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId() + "_inbound",
                mqttInClient(), inboundTopics);
        adapter.addTopic();  // 添加 TOPICS
        adapter.setCompletionTimeout(1000 * 5);
        adapter.setQos(0);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    // 通过通道获取数据,即处理 MQTT 发送过来的消息，可以通过 MQTTX 工具发送数据测试
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")  // 异步处理
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                Object payload = message.getPayload();
                MessageHeaders messageHeaders = message.getHeaders();
                UUID packetId = messageHeaders.getId();
                Object qos = messageHeaders.get(MqttHeaders.QOS);
                String recvTopic = (String) messageHeaders.get(MqttHeaders.RECEIVED_TOPIC);
                assert recvTopic != null;
                if (recvTopic.startsWith("edgex")) {
                    String handMessage = "MQTT Client " + "packetId ===>" + packetId
                            + "\nReceive payLoad ===> " + payload
                            + " \tQOS ===> " + qos
                            + "\n Topics: " + recvTopic;

                    LOGGER.debug(handMessage);

                    //TODO 加入消息队列中，再做持久化  2022年11月8日
                    updateOrStoreSensorData(recvTopic, payload);
                }
            }
        };
    }

    private void updateOrStoreSensorData(String recvTopic, Object payload) {
        String[] topicArr = recvTopic.split("/");
        String datasource = topicArr[4];
        String sensorTag = topicArr[4];

        if (payload !=null){
            if (datasource=="ruleSource"){
                DetectionResult result = JSONUtil.toBean((String) payload, DetectionResult.class);
                RuleSource rulesource = new RuleSource();
                DetectRule detectRul = new DetectRule();
                rulesource.setId(result.getId());
                rulesource.setScenes_id(result.getScenes_id());
                rulesource.setScene_version(result.getScene_version());
                rulesource.setArea_id(result.getArea_id());
                rulesource.setPoint_id(result.getPoint_id());
                rulesource.setDetection_tag(result.getDetection_tag());
                rulesource.setDetection_no(result.getDetection_no());
                rulesource.setImage_id(result.getImage_id());
                rulesource.setEvent_time(result.getEvent_time());
                List detect_rule = result.getDetection_rule();
                for (int i=0;i<detect_rule.size();i++){
                    DetectionRule detectionRule = JSONUtil.toBean((String) detect_rule.get(i),DetectionRule.class);
                    //DetectRule detectRul = new DetectRule();
                    detectRul.setDetection_item(detectionRule.getDetection_item());
                    detectRul.setDetection_key(detectionRule.getDetection_key());
                    detectRul.setDetection_value(detectionRule.getDetection_value());
                    detectRul.setPoint_sub_id(detectionRule.getPoint_sub_id());
                    detectRul.setImage_id(result.getImage_id());
                    detectRul.setDetection_no(result.getDetection_no());
                    detectRul.setDetection_tag(result.getDetection_tag());
                    detectRul.setPoint_id(result.getPoint_id());

                }
                ruleSourceService.save(rulesource);
                detectRuleService.save(detectRul);

            }
            else if (datasource == "ruleHandle"){
                MqttCmds cmds = JSONUtil.toBean((String) payload, MqttCmds.class);
                String id = cmds.getCmdValue();
                String scenes_id = cmds.getCmdValue();
                String scene_version = cmds.getCmdValue();
                String area_id = cmds.getCmdValue();
                String detection_no = cmds.getCmdValue();
                String detection_tag = cmds.getCmdValue();
                String warn = cmds.getCmdValue();
                String point_collect = cmds.getCmdValue();
                String image_collect = cmds.getCmdValue();
                String event_time = cmds.getCmdValue();
                RuleHandle rulehandle = new RuleHandle();
                rulehandle.setWarn(Integer.parseInt(warn));
                rulehandle.setArea_id(area_id);
                rulehandle.setId(id);
                rulehandle.setScenes_id(scenes_id);
                rulehandle.setScene_version(scene_version);
                rulehandle.setDetection_no(Integer.parseInt(detection_no));
                rulehandle.setEvent_time(Integer.parseInt(event_time));
                rulehandle.setDetection_tag(detection_tag);
                rulehandle.setPoint_collect(point_collect);
                rulehandle.setImage_collect(image_collect);
                ruleHandleService.save(rulehandle);



            } else if (datasource == "ruleAggregation") {
                MqttCmds cmds = JSONUtil.toBean((String) payload, MqttCmds.class);
                String id = cmds.getCmdValue();
                String scenes_id = cmds.getCmdValue();
                String scene_version = cmds.getCmdValue();
                String area_id = cmds.getCmdValue();
                String detection_no = cmds.getCmdValue();
                String detection_tag = cmds.getCmdValue();
                String warn = cmds.getCmdValue();
                String point_collect = cmds.getCmdValue();
                String image_collect = cmds.getCmdValue();
                String event_time = cmds.getCmdValue();
                RuleAggregation ruleAggregation = new RuleAggregation();
                ruleAggregation.setWarn(Integer.parseInt(warn));
                ruleAggregation.setArea_id(area_id);
                ruleAggregation.setId(id);
                ruleAggregation.setScenes_id(scenes_id);
                ruleAggregation.setScene_version(scene_version);
                ruleAggregation.setDetection_no(Integer.parseInt(detection_no));
                ruleAggregation.setEvent_time(Integer.parseInt(event_time));
                ruleAggregation.setDetection_tag(detection_tag);
                ruleAggregation.setPoint_collect(point_collect);
                ruleAggregation.setImage_collect(image_collect);
                ruleAggregationService.save(ruleAggregation);

            } else if (datasource == "ruleAggregation") {
                MqttCmds cmds = JSONUtil.toBean((String) payload, MqttCmds.class);
                String id = cmds.getCmdValue();
                String scenes_id = cmds.getCmdValue();
                String scene_version = cmds.getCmdValue();
                String area_id = cmds.getCmdValue();
                String detection_no = cmds.getCmdValue();
                String detection_tag = cmds.getCmdValue();
                String warn = cmds.getCmdValue();
                String point_collect = cmds.getCmdValue();
                String image_collect = cmds.getCmdValue();
                String event_time = cmds.getCmdValue();
                RuleAggregation ruleAggregation = new RuleAggregation();
                ruleAggregation.setWarn(Integer.parseInt(warn));
                ruleAggregation.setArea_id(area_id);
                ruleAggregation.setId(id);
                ruleAggregation.setScenes_id(scenes_id);
                ruleAggregation.setScene_version(scene_version);
                ruleAggregation.setDetection_no(Integer.parseInt(detection_no));
                ruleAggregation.setEvent_time(Integer.parseInt(event_time));
                ruleAggregation.setDetection_tag(detection_tag);
                ruleAggregation.setPoint_collect(point_collect);
                ruleAggregation.setImage_collect(image_collect);
                ruleAggregationService.save(ruleAggregation);


            } else if (datasource == "ruleWarn") {
                MqttCmds cmds = JSONUtil.toBean((String) payload, MqttCmds.class);
                String id = cmds.getCmdValue();
                String scenes_id = cmds.getCmdValue();
                String scene_version = cmds.getCmdValue();
                String area_id = cmds.getCmdValue();
                //String detection_no = cmds.getCmdValue();
                String detection_tag = cmds.getCmdValue();
                String warn = cmds.getCmdValue();
                String warn_row = cmds.getCmdValue();
                String warn_index = cmds.getCmdValue();
                String image_id = cmds.getCmdValue();
                String image_collect_row = cmds.getCmdValue();
                String event_time = cmds.getCmdValue();
                RuleWarn ruleWarn = new RuleWarn();
                ruleWarn.setId(id);
                ruleWarn.setScenes_id(scenes_id);
                ruleWarn.setScene_version(scene_version);
                ruleWarn.setArea_id(area_id);
                ruleWarn.setDetection_tag(detection_tag);
                ruleWarn.setWarn(Integer.parseInt(warn));
                ruleWarn.setWarn_row(warn_row);
                ruleWarn.setWarn_index(warn_index);
                ruleWarn.setImage_id(image_id);
                ruleWarn.setImage_collect_row(image_collect_row);
                ruleWarn.setEvent_time(Integer.parseInt(event_time));
                ruleWarnService.save(ruleWarn);



            }
        }

            // 保存到数据库
            //.save(sensor);
            // 缓存到Redis列表存储
            //redisUtils.lLeftPush("iot:deviceId:" + deviceId + ":sensorTag:" + sensorTag, sensorVal);
        }

    public static void main(String[] args) {


    }
    }




