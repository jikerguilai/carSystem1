package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.dto.FuzzyQueryDto;
import com.jkxy.car.api.service.InitService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author spxxkw
 * @date 2022/09/03
 */

@RestController
@RequestMapping("init")
public class InitController {

    @Autowired
    private InitService initService;

    @PostMapping("start")
    public JSONResult startInit() {
        initService.init();
        return JSONResult.ok();
    }
}
