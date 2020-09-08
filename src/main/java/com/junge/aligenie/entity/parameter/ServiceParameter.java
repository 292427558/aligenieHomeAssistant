package com.junge.aligenie.entity.parameter;

import com.junge.aligenie.entity.DeviceTypeOperation;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * homeassistant 服务调用 参数表
 *
 * @Author LiuJun
 * @Date 2020/9/8 11:17
 */
@Entity
@Data
@Table(name = "hass_service_parameter")
public class ServiceParameter {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "parameter",nullable = false, length = 255)
    @NotEmpty(message = "参数名不可为空")
    private String parameterName;

    /*
     * 1  不转化  2 转化
     **/
    @Column(name = "is_conversion",nullable = false, length = 1)
    @NotEmpty(message = "类别")
    private String isConversion = "1";

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "service_parameter_id",referencedColumnName = "id")
    private List<ServiceParameterrConversion> serviceParameterrConversions;

    @ManyToOne
    @JoinColumn(name = "device_type_operation_id")
    private DeviceTypeOperation deviceTypeOperation;
}
