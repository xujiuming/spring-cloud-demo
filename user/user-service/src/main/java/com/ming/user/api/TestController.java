package com.ming.user.api;

import com.ming.common.api.service.ITestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
    private ITestService testService;

    @GetMapping("/test")
    public String test(){
        return testService.hello("userTest");
    }
}
