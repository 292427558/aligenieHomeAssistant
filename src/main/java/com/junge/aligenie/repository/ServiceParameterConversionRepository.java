package com.junge.aligenie.repository;

import com.junge.aligenie.entity.parameter.ServiceParameter;
import com.junge.aligenie.entity.parameter.ServiceParameterrConversion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/8/11 10:29
 */
public interface ServiceParameterConversionRepository extends JpaRepository<ServiceParameterrConversion,String> {

    //List<ServiceParameter> getServiceParametersByDeviceTypeOperation_Id(String id);
}
