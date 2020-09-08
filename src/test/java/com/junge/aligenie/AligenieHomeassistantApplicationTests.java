package com.junge.aligenie;


import com.junge.aligenie.entity.DeviceType;
import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.entity.Operation;
import com.junge.aligenie.entity.parameter.ServiceParameter;
import com.junge.aligenie.entity.parameter.ServiceParameterrConversion;
import com.junge.aligenie.repository.DeviceTypeOperationRepository;
import com.junge.aligenie.repository.DeviceTypeRepository;
import com.junge.aligenie.repository.OperationRepository;
import com.junge.aligenie.service.DeviceTypeOperationService;
import com.junge.aligenie.service.DiscoverDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AligenieHomeassistantApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(2);
    }

    @Autowired
    DiscoverDeviceService discoverDeviceService;


    @Autowired
    DeviceTypeOperationRepository deviceTypeOperationRepository;

    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    DeviceTypeOperationService deviceTypeOperationService;

    @Test
    public void getDiscoverResult() {
        discoverDeviceService.DiscoverDevice(null);
    }

    @Test
    public void testDeviceTypeoperation(){
        //查询两个数据
        Optional<Operation> operation = operationRepository.findById("297e9fa4741e88ed01741e8fd65e0000");
        Optional<DeviceType> deviceType = deviceTypeRepository.findById("4efbbeb0765b4aa4b60fe6a9ea41c26a");
        System.out.println(operation.get());
        System.out.println(deviceType.get());

        //添加一个中间表数据
        DeviceTypeOperation deviceTypeOperation = new DeviceTypeOperation();
        deviceTypeOperation.setService("switch.turn_on");
        deviceTypeOperation.setDeviceType(deviceType.get());
        deviceTypeOperation.setOperation(operation.get());
        deviceTypeOperation.setRequestType("1");
        deviceTypeOperation.setResponseName("TurnOnResponse");
        DeviceTypeOperation save = deviceTypeOperationRepository.save(deviceTypeOperation);
    }

    @Test
    public void testDeviceTypequery(){
        //查询两个数据
        Optional<Operation> operation = operationRepository.findById("297e9fa4741e88ed01741e8fd65e0000");
        Optional<DeviceType> deviceType = deviceTypeRepository.findById("4efbbeb0765b4aa4b60fe6a9ea41c26a");
        Optional<DeviceTypeOperation> byId = deviceTypeOperationRepository.findById("0c7721d1-21ed-4ced-85e0-50909eb630ef");

        System.out.println(operation.get());
        System.out.println(deviceType.get());
    }


    /**
     * 测试参数及转换 及 级联保存
     * @Author LiuJun
     * @Date 2020/9/8 11:47
     * @return void
     **/
    @Test
    public void testParemeterConversion(){
        //查询两个数据  操作 设置模式  设备类型  空调
        Optional<Operation> operation = operationRepository.findById("297e9fa4741e88ed01741ed9d6380027");
        Optional<DeviceType> deviceType = deviceTypeRepository.findById("4ac26e47896d46c493d27a888e1a6858");

        //设置homeassistant的参数
        ServiceParameter serviceParameter = new ServiceParameter();
        serviceParameter.setIsConversion("2");
        serviceParameter.setParameterName("hvac_mode");
        //设置参数转化
        ArrayList<ServiceParameterrConversion> serviceParameterrConversions = new ArrayList<>();
        ServiceParameterrConversion serviceParameterrConversion1 = new ServiceParameterrConversion();
        serviceParameterrConversion1.setAliParameterAttr("mode");
        serviceParameterrConversion1.setAliParameterValue("auto");
        serviceParameterrConversion1.setHassParameter("auto");
        ServiceParameterrConversion serviceParameterrConversion2 = new ServiceParameterrConversion();
        serviceParameterrConversion2.setAliParameterAttr("mode");
        serviceParameterrConversion2.setAliParameterValue("cold");
        serviceParameterrConversion2.setHassParameter("cool");
        serviceParameterrConversions.add(serviceParameterrConversion1);
        serviceParameterrConversions.add(serviceParameterrConversion2);

        //参数关联 参数转化
        serviceParameter.setServiceParameterrConversions(serviceParameterrConversions);

        //添加一个操作设备表数据数据
        DeviceTypeOperation deviceTypeOperation = new DeviceTypeOperation();
        deviceTypeOperation.setService("climate.set_hvac_mode");
        deviceTypeOperation.setDeviceType(deviceType.get());
        deviceTypeOperation.setOperation(operation.get());
        deviceTypeOperation.setRequestType("1");
        deviceTypeOperation.setResponseName("SetModeResponse");

        //关联参数
        deviceTypeOperation.setServiceParameters(Collections.singletonList(serviceParameter));
        //保存  级联保存
        DeviceTypeOperation save = deviceTypeOperationRepository.save(deviceTypeOperation);
    }


    @Test
    public  void testQuery(){
        DeviceTypeOperation deviceTypeOperation = deviceTypeOperationService.getDeviceTypeOperation("aircondition", "SetMode");
        System.out.println(deviceTypeOperation);
    }
}
