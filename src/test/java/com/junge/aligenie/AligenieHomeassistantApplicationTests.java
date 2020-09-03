package com.junge.aligenie;


import com.junge.aligenie.service.DiscoverDeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AligenieHomeassistantApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(2);
    }

    @Autowired
    DiscoverDeviceService discoverDeviceService;

    @Test
    public void getDiscoverResult() {
        discoverDeviceService.DiscoverDevice(null);
    }

}
