package com.chengfu.springBootSample.service.impl;


import org.springframework.stereotype.Service;

import com.chengfu.springBootSample.bean.ReserveLoginRequestBean;
import com.chengfu.springBootSample.service.ReserveService;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class RedundantServiceImpl implements ReserveService {
    
    @Override
    public String getReserveSignal (String UserPK) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    @Override
    public String getReserveToken (ReserveLoginRequestBean request) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
