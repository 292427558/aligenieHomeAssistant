package com.junge.aligenie.repository;

import com.junge.aligenie.entity.Device;
import com.junge.aligenie.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/8/11 10:29
 */
public interface DeviceTypeRepository extends JpaRepository<DeviceType,String> {
}
