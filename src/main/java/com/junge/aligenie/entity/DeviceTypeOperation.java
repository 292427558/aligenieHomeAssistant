package com.junge.aligenie.entity;

import com.junge.aligenie.entity.parameter.ServiceParameter;
import com.junge.aligenie.entity.parameter.ServiceParameterrConversion;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

    @Column(name = "service", nullable = false, length = 128)
    @NotEmpty(message = "服务不可为空")
    private String service;

    @Column(name = "response_name", nullable = false, length = 128)
    @NotEmpty(message = "响应代码不可为空")
    private String responseName;

    /*
     * 1  控制操作  2 查询操作
     **/
    @Column(name = "request_type",nullable = false, length = 1)
    @NotEmpty(message = "类别")
    private String requestType = "1";

    @ManyToOne
    @JoinColumn(name = "deviceType_id",referencedColumnName = "id")
    private DeviceType deviceType;

    @ManyToOne
    @JoinColumn(name = "operation_id",referencedColumnName = "id")
    private Operation operation;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)//指定抓取策略
    @JoinColumn(name = "device_type_operation_id",referencedColumnName = "id")
    private List<ServiceParameter> serviceParameters;
}
