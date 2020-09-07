package com.junge.aligenie.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/7 14:31
 */
@Entity
@Data
@Table(name = "hass_device_type_operation")
public class DeviceTypeOperation {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "service", unique = true, nullable = false, length = 128)
    @NotEmpty(message = "服务不可为空")
    private String service;

    @ManyToOne
    @JoinColumn(name = "deviceType_id",referencedColumnName = "id")
    private DeviceType deviceType;

    @ManyToOne
    @JoinColumn(name = "operation_id",referencedColumnName = "id")
    private Operation operation;

}
