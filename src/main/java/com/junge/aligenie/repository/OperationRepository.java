package com.junge.aligenie.repository;

import com.junge.aligenie.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/8/11 10:29
 */
public interface OperationRepository extends JpaRepository<Operation,String> {
}
