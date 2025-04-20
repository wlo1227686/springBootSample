package com.chengfu.springBootSample.controller;


import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chengfu.springBootSample.bean.ReserveLoginRequestBean;
import com.chengfu.springBootSample.bean.ResultBean;
import com.chengfu.springBootSample.service.ReserveService;
import com.chengfu.springBootSample.utils.LogUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;




@RestController
@RequestMapping ("/reserve")
@Tag (name = "備援登入功能")
public class ReserveController {
    
    @Autowired
    private ReserveService reserveService;
    /* ************************************************************************** *
     * 
     * TAG Redundant
     * 
     * ************************************************************************** */
    
    @GetMapping ("/{userpk}/signal")
    @Operation (summary = "取得備援登入Signal")
    public ResultBean<String> getReserveSignal (@PathVariable ("userpk") String userPK) {
        
        ZonedDateTime startTime = ZonedDateTime.now ();
        LogUtils.logInput (startTime);
        
        ResultBean<String> resultBean = new ResultBean<String> (reserveService.getReserveSignal (userPK));
        
        LogUtils.logOutput (startTime);
        return resultBean;
    }
    
    
    @PostMapping ("/token")
    @Operation (summary = "由備援登入機制取得 JWT Token")
    public ResultBean<String> getReserveToken (@Valid @RequestBody ReserveLoginRequestBean requestBean) {
        
        ZonedDateTime startTime = ZonedDateTime.now ();
        LogUtils.logInput (startTime);
        
        // JWT Token
        ResultBean<String> resultBean = new ResultBean<String> (reserveService.getReserveToken (requestBean));
        
        LogUtils.logOutput (startTime);
        return resultBean;
    }
}
