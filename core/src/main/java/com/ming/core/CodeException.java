package com.ming.core;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务异常
 *
 * @author ming
 * @date 2019-04-02 11:26:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CodeException extends RuntimeException {

    private CodeEnum codeEnum;
    private String extraInfo;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CodeException(CodeEnum codeEnum, String extraInfo) {
        super(extraInfo);
        this.codeEnum = codeEnum;
        this.extraInfo = extraInfo;
    }


    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CodeException(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

}
