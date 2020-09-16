package com.junge.aligenie.entity.parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * homeassistant 服务调用 参数转化表 用于转化 tmall和 homeassistant的参数
 *
 * @Author LiuJun
 * @Date 2020/9/8 11:18
 */
@Entity
@Data
@Table(name = "hass_service_parameterr_conversion")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @NotEmpty(message = "homeassistant参数不可为空")
    private String hassParameter;

    @ManyToOne
    @JoinColumn(name = "service_parameter_id")
    private ServiceParameter serviceParameter;

    @Override
    public String toString() {
        return "ServiceParameterrConversion{" +
                "id='" + id + '\'' +
                ", aliParameterAttr='" + aliParameterAttr + '\'' +
                ", aliParameterValue='" + aliParameterValue + '\'' +
                ", hassParameter='" + hassParameter + '\'' +
                '}';
    }
}
