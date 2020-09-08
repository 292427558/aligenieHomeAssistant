package com.junge.aligenie.entity.parameter;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/8 11:18
 */
@Entity
@Data
@Table(name = "hass_service_parameterr_conversion")
public class ServiceParameterrConversion {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;


    @Column(name = "ali_parameter_attr",nullable = false, length = 255)
    //@NotEmpty(message = "天猫请求参数payload中attribute")
    private String aliParameterAttr;

    @Column(name = "ali_parameter_value",nullable = false, length = 255)
    //@NotEmpty(message = "天猫请求参数payload中value")
    private String aliParameterValue;

    @Column(name = "hass_parameter",nullable = false, length = 255)
    @NotEmpty(message = "homeassistant参数")
    private String hassParameter;

    @ManyToOne
    @JoinColumn(name = "service_parameter_id")
    private ServiceParameter serviceParameter;
}
