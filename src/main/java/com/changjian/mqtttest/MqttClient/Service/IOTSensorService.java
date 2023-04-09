package com.changjian.mqtttest.MqttClient.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.changjian.mqtttest.MqttClient.domain.IOTSensor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Spring
 * @description 针对表【tb_sensor】的数据库操作Service
 * @createDate 2022-11-08 10:14:23
 */
public interface IOTSensorService extends IService<IOTSensor> {


    IPage<IOTSensor> getSensorDataByDeviceIdByOpr(Integer deviceId, Integer pageNo, Integer pageSize);

    IPage<IOTSensor> getSensorDataByDeviceIdAndSensorTagByOpr(Integer deviceId, String sensorTag, Integer pageNo, Integer pageSize);

    IOTSensor getLatestSensorVal(Integer deviceId, String sensorTag);

    List<IOTSensor> getSensorDataByDeviceIdAndSensorTagByDay(Integer deviceId, String sensorTag, Date startDate, Date endData);

    Map<String, Object> getSensorDataByDeviceIdSimpleByOpr(Integer deviceId, Integer pageNo, Integer pageSize);

    List<Object> getSensorDataByDeviceIdAndSensorTagSimpleByOpr(Integer deviceId, String sensorTag, Integer pageNo, Integer pageSize);

    Map<String, Object> getLatestSensorValSimple(Integer deviceId);
}

