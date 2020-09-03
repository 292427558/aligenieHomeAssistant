package com.junge.aligenie.web;

import com.junge.aligenie.entity.Operation;
import com.junge.aligenie.repository.OperationRepository;
import com.junge.aligenie.utils.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/13 15:07
 */
@RequestMapping("/setting")
@Controller
public class OperationController {


    @Autowired
    OperationRepository operationRepository;



    @PostMapping("/operation")
    @ResponseBody
    public ReturnT addOperation(@Valid Operation operation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ReturnT(400,bindingResult.getFieldError().getDefaultMessage());
        }
        Operation save = operationRepository.save(operation);
        if(save!=null){
            return new ReturnT(200,"新增成功");
        }
        return new ReturnT(500,"新增失败");
    }

    @GetMapping("/operationLists")
    @ResponseBody
    public Page<Operation> getOperations(Integer pageNum, Integer pageSize){
        if(pageNum==null){
            pageNum=0;
            pageSize=10;
        }
        Page<Operation> all = operationRepository.findAll(PageRequest.of(pageNum, pageSize));
        return  all;
    }

    @DeleteMapping("/operation/{id}")
    @ResponseBody
    public ReturnT delOperations(@PathVariable("id") String id){
        operationRepository.deleteById(id);
        return new ReturnT(200,"删除成功");
    }

}
