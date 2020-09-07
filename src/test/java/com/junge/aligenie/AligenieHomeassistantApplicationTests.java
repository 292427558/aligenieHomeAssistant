package com.junge.aligenie;


import com.junge.aligenie.entity.DeviceType;
import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.entity.Operation;
import com.junge.aligenie.repository.DeviceTypeOperationRepository;
import com.junge.aligenie.repository.DeviceTypeRepository;
import com.junge.aligenie.repository.OperationRepository;
import com.junge.aligenie.service.DiscoverDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
