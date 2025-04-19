package com.chengfu.springBootSample.bean;


import com.chengfu.springBootSample.enums.ResultCode;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;




@Data
public class ResultBean<T> {
    
    @Parameter (description = "回傳代碼")
    private String responseCode;
    
    @Parameter (description = "回傳訊息")
    private String responseDesc;
    
    @Parameter (description = "回傳資料")
    private T responseBody;
    
    public ResultBean () {
        super ();
    }
    
    
    public ResultBean (String responseCode, String responseDesc) {
        super ();
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }
    
    
    public ResultBean (ResultCode resultCode) {
        super ();
        this.responseCode = resultCode.getResponseCode ();
        this.responseDesc = resultCode.getResponseDesc ();
    }
    
    
    public ResultBean (ResultCode resultCode, T responseBody) {
        super ();
        this.responseCode = resultCode.getResponseCode ();
        this.responseDesc = resultCode.getResponseDesc ();
        this.responseBody = responseBody;
    }
    
    
    public ResultBean (T responseBody) {
        super ();
        this.responseBody = responseBody;
        if (responseBody == null) {
            this.responseCode = ResultCode.DATA_NOT_FIND.getResponseCode ();
            this.responseDesc = ResultCode.DATA_NOT_FIND.getResponseDesc ();
        } else {
            this.responseCode = ResultCode.SUCCESS.getResponseCode ();
            this.responseDesc = ResultCode.SUCCESS.getResponseDesc ();
        }
    }
}
