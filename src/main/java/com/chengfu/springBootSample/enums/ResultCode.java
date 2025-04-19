package com.chengfu.springBootSample.enums;


public enum ResultCode {
    
    // Result Bean專用
    DATA_NOT_FIND("DATA_NOT_FIND", "查無資料"),
    
    SUCCESS("SUCCESS", "執行成功");
    
    
    private String responseCode;
    
    private String responseDesc;
    
    private ResultCode (String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }
    
    
    public String getResponseCode () {
        return responseCode;
    }
    
    
    public String getResponseDesc () {
        return responseDesc;
    }
}
