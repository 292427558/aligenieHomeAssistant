package com.junge.aligenie.repository;

import com.junge.aligenie.entity.Device;
import com.junge.aligenie.entity.parameter.ServiceParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/8/11 10:29
 */
public interface ServiceParameterRepository extends JpaRepository<ServiceParameter,String> {

    List<ServiceParameter> getServiceParametersByDeviceTypeOperation_Id(String id);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    void deleteServiceParameterById(String  id);
}
