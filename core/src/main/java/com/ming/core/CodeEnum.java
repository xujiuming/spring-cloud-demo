package com.ming.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 项目返回信息状态码
 * 此处http状态码 按照项目情况进行选择 不一定按照状态码返回 具体的参考项目的全局异常处理
 *
 * @author ming
 * @date 2019-04-02 11:04:11
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {
    /**
     * 应用服务内部异常
     */
    SERVER_ERROR(-1, HttpStatus.INTERNAL_SERVER_ERROR, "服务异常!"),
    /**
     * 服务未准备好 此异常 仅限于健康检测相关的错误
     */
    SERVER_NOT_READY(-2, HttpStatus.SERVICE_UNAVAILABLE, "后端服务暂未准备好!请稍后再试"),


    /**
     * 数据未找到
     */
    DATA_NOT_FOUND(40000, HttpStatus.BAD_REQUEST, "data not found！ "),

    /**
     * 登录异常
     */
    TOKEN_ERROR(400001, HttpStatus.FORBIDDEN, "token错误!"),
    USER_NOT_LOGIN(40002, HttpStatus.FORBIDDEN, "用戶未登录"),
    /**
     * 访问上下文异常
     */
    ACCESS_CONTEXT_ERROR(40003, HttpStatus.BAD_REQUEST, "access context error!!"),

    /**
     * 非法请求
     */
    REQUEST_ILLEGAL(40004, HttpStatus.FORBIDDEN, "非法请求！"),
    /**
     * 数据校验失败
     */
    DATA_CHECK_ERROR(40005, HttpStatus.BAD_REQUEST, "数据校验失败!"),
    ;
    private Integer code;
    private HttpStatus httpStatus;
    private String message;
}
