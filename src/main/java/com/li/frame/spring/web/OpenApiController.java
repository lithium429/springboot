package com.li.frame.spring.web;

import com.li.frame.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/open")
public class OpenApiController {

    @GetMapping("/info")
    public Object info(){
        return new HashMap<>(){{
            put("name","open");
            put("data","");
        }};
    }

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public Object userOne(){
        return userService.getOne();
    }

}
