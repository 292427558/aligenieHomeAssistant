package com.junge.aligenie;

import com.junge.aligenie.utils.RestHomeAssistantUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/6 14:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AligenieHomeassistantTest {

    @Test
    public void test01() {
        System.out.println(RestHomeAssistantUtils.getHomeToken());
    }
}
