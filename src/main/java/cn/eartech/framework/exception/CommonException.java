package cn.eartech.framework.exception;

import cn.eartech.framework.dto.CodeMsg;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shanfa
 * @Desc
 * @date 2020/3/27
 * @Version 1.0
 */
@Getter
@Setter
public class CommonException extends RuntimeException {
    /**
     * 错误码
     */
    protected int errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;


    public CommonException() {
        super();
    }

    public CommonException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public CommonException(CodeMsg codeMsg) {
        super(String.valueOf(codeMsg.getCode()));
        this.errorCode = codeMsg.getCode();
        this.errorMsg = codeMsg.getMsg();
    }

    public CommonException(int errorCode,String message) {
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = message;
    }


    public CommonException(int errorCode,String message,Throwable cause) {
        super(String.valueOf(errorCode),cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }
}
