package com.junge.aligenie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/11 11:45
 */
@RequestMapping("/")
@Controller
public class PageController {


    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/page/{type}/{path}")
    public String topage(@PathVariable("path") String path,@PathVariable("type") String type){
        return type+"/"+path;
    }



}
