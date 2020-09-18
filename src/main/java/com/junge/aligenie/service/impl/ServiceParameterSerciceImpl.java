package com.junge.aligenie.service.impl;

import com.junge.aligenie.repository.ServiceParameterRepository;
import com.junge.aligenie.service.ServiceParameterSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/17 9:27
 */
@Service
public class ServiceParameterSerciceImpl implements ServiceParameterSercice {

    @Autowired
    ServiceParameterRepository serviceParameterRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletebyId(String id) {
        serviceParameterRepository.deleteServiceParameterById(id);
        //serviceParameterRepository.flush();
    }
}
