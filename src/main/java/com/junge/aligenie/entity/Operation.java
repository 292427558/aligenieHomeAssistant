package com.junge.aligenie.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 天猫精灵 的操作类别 基础表 估计40多个操作类
 *
 * @Author LiuJun
 * @Date 2020/8/11 10:04
 */
@Entity
@Data
@Table(name = "hass_operation")
public class Operation {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "name", unique = true, nullable = false, length = 128)
    @NotEmpty(message = "操作代码不可为空")
    private String name;

    @Column(name = "friendly_name", unique = true, nullable = false, length = 128)
    @NotEmpty(message = "操作名称不可为空")
    private String friendlyName;
}
