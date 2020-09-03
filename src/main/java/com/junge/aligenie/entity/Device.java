package com.junge.aligenie.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * mode class
 * homeAssistant 的设备信息 在天猫发现设备时会从这儿返回设备信息
 * @Author LiuJun
 * @Date 2020/8/13 15:49
 */
@Entity
@Data
@Table(name = "hass_device")
public class Device {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "entity_id", unique = true, nullable = false, length = 256)
    @NotEmpty(message = "实体id不可为空")
    private String entityId;

    @Column(name = "device_name", nullable = false, length = 256)
    @NotEmpty(message = "设备名称不可为空")
    private String deviceName;

    @Column(name = "device_type", nullable = false, length = 256)
    @NotEmpty(message = "设备类型不可为空")
    private String deviceType;


    @Column(name = "device_zone", nullable = false, length = 256)
    //@NotEmpty(message = "设备名称不可为空")
    private String deviceZone;

    @Column(name = "brand",  nullable = true, length = 256)
    private String brand;

    @Column(name = "model", nullable = true, length = 256)
    private String model;
}
