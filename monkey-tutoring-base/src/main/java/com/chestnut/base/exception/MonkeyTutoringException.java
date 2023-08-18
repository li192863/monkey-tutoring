package com.chestnut.base.exception;


/**
 * @author Mr.M
 * @version 1.0
 * @description 学成在线项目异常类
 * @date 2022/9/6 11:29
 */
public class MonkeyTutoringException extends RuntimeException {
    /**
     * 错误信息
     */
    private String errMessage;

    public MonkeyTutoringException() {
        super();
    }

    public MonkeyTutoringException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError) {
        throw new MonkeyTutoringException(commonError.getErrMessage());
    }

    public static void cast(String errMessage) {
        throw new MonkeyTutoringException(errMessage);
    }

}
