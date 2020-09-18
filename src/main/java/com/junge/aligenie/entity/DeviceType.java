package com.junge.aligenie.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * mode class
 *  天猫的支持品类  共计109个
 * @Author LiuJun
 * @Date 2020/8/13 17:14
 */
@Entity
@Data
@Table(name = "hass_device_type")
public class DeviceType {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "name", unique = true, nullable = false, length = 256)
    private String name;

    @Column(name = "englishName", nullable = false, length = 256)
    private String englishName;

    @OneToMany(targetEntity=DeviceTypeOperation.class,mappedBy = "operation",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Operation> operations = new ArrayList<>();
}
