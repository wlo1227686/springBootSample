package com.chengfu.springBootSample.service;

import com.chengfu.springBootSample.bean.ReserveLoginRequestBean;

public interface ReserveService {
    
    /**
     * 取得備援登入 Signal
     * 
     * @param UserPK
     * @return Signal
     */
    public String getReserveSignal (String UserPK);
    
    
    /**
     * 取得備援登入 Token
     * 
     * @param request
     * @return
     */
    public String getReserveToken (ReserveLoginRequestBean request);
}
