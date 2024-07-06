package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.CountYieldChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class homeController {
    @Autowired
    private CountYieldChatService  countYieldChatService;

    @GetMapping("/lineYoidDate")
    public AjaxResult lineDate(){

        return AjaxResult.success(countYieldChatService.countYieldChat());
    }
}
