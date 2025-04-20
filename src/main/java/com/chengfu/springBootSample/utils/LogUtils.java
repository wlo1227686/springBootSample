package com.chengfu.springBootSample.utils;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Component
public class LogUtils {
    
    
    // 結構化log訊息
    public final static String ACCESS_LOG_MSG = "access";
    
    // INPUT
    private final static String INPUT = "input";
    
    // OUTPUT
    private final static String OUTPUT = "output";
    
    // 回傳代碼
    private final static String STATUS_CODE = "statusCode";
    
    // 開始時間
    private final static String START_TIME_STAMP = "startTimeStamp";
    
    // 結束時間
    private final static String END_TIME_STAMP = "endTimeStamp";
    
    // 處理時間
    private final static String RESPONSE_TIME = "responseTime";
    
    // 時間格式
    private final static String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'";
    
    @Autowired
    private static ObjectMapper objectMapper;
    
    @Autowired
    public LogUtils (ObjectMapper objectMapper) {
        LogUtils.objectMapper = objectMapper;
    }
    
    
    /**
     * log input
     * 
     * @param <T>
     * @param startTime
     */
    public static <T> void logInput (ZonedDateTime startTime) {
        logInput (startTime, null);
    }
    
    
    /**
     * log input
     * 
     * @param <T>
     * @param startTime
     * @param input
     */
    public static <T> void logInput (ZonedDateTime startTime, T input) {
        if (startTime != null) {
            ThreadContext.put (LogUtils.START_TIME_STAMP, DateTimeFormatter.ofPattern (TIME_FORMAT).format (startTime));
        }
        
        if (input != null) {
            try {
                // 試著轉成 json字串, 失敗就直接放
                ThreadContext.put (LogUtils.INPUT, objectMapper.writeValueAsString (input));
            } catch (Exception e) {
                ThreadContext.put (LogUtils.INPUT, input.toString ());
            }
        } else {
            ThreadContext.put (LogUtils.INPUT, "N/A");
        }
    }
    
    
    /**
     * log output
     * 
     * @param <T>
     * @param httpStatus
     * @param startTime
     */
    public static <T> void logOutput (ZonedDateTime startTime) {
        logOutput (HttpStatus.OK, startTime, null);
    }
    
    
    /**
     * log output
     * 
     * @param <T>
     * @param httpStatus
     * @param startTime
     * @param output
     */
    public static <T> void logOutput (HttpStatus httpStatus, ZonedDateTime startTime, T output) {
        ZonedDateTime endTime = ZonedDateTime.now ();
        ThreadContext.put (LogUtils.END_TIME_STAMP, DateTimeFormatter.ofPattern (TIME_FORMAT).format (endTime));
        
        // 計算執行時間
        if (startTime != null) {
            ThreadContext.put (LogUtils.RESPONSE_TIME,
                    String.valueOf (endTime.toInstant ().toEpochMilli () - startTime.toInstant ().toEpochMilli ()));
        }
        
        if (httpStatus != null) {
            ThreadContext.put (LogUtils.STATUS_CODE, String.valueOf (httpStatus.value ()));
        }
        
        if (output != null) {
            try {
                // 試著轉成 json 字串, 失敗就直接放
                ThreadContext.put (LogUtils.OUTPUT, objectMapper.writeValueAsString (output));
            } catch (Exception e) {
                ThreadContext.put (LogUtils.OUTPUT, output.toString ());
            }
        } else {
            ThreadContext.put (LogUtils.OUTPUT, "N/A");
        }
        
        log.info (ACCESS_LOG_MSG);
        clearThreadContext ();
    }
    
    
    /**
     * 清除內容
     */
    private static void clearThreadContext () {
        ThreadContext.remove (LogUtils.INPUT);
        ThreadContext.remove (LogUtils.OUTPUT);
        ThreadContext.remove (LogUtils.STATUS_CODE);
        ThreadContext.remove (LogUtils.START_TIME_STAMP);
        ThreadContext.remove (LogUtils.END_TIME_STAMP);
        ThreadContext.remove (LogUtils.RESPONSE_TIME);
    }
    
    
}
