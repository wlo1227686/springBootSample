package com.chengfu.springBootSample.bean;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;




/**
 * 
 */
@Data
@Schema (description = "Reserve登入 輸入格式")
public class ReserveLoginRequestBean {
    
    @NotBlank (message = "請輸入userPK")
    @Schema (description = "userPK", example = "string")
    private String userPK;
    
    
    @NotBlank (message = "請輸入signal")
    @Schema (description = "signal", example = "string")
    private String signal;
}
