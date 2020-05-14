package cn.eartech.framework.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回码0表示成功 非0表示失败
 * @author shanfa
 * @param <T> 返回的数据类型
 */
@Getter
@Setter
@ToString
public class ResultDTO<T> implements Serializable {
    private int code;

    private String msg;

    private T data;

    public ResultDTO(){
        this.code = 0;
        this.msg = "success";
        this.data = null;
    }

    public ResultDTO(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }
    public ResultDTO(int errorCode, String errorMsg){
        this.code = errorCode;
        this.msg = errorMsg;
        this.data = null;
    }


    public ResultDTO(CodeMsg codeMsg){
        if(codeMsg==null){
            return;
        }else {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
            this.data = null;
        }
    }

    public ResultDTO(CodeMsg codeMsg,T data){
        if(codeMsg==null){
            return;
        }else {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
            this.data = data;
        }
    }

    public static <T> ResultDTO<T> success(){
        return new ResultDTO<T>();
    }

    public static <T> ResultDTO<T> success(T data){ return new ResultDTO<T>(data); }


    public static <T> ResultDTO<T> error(int errorCode, String errorMsg ){
        return new ResultDTO<T>(errorCode,errorMsg);
    }
    public static <T> ResultDTO<T> error(CodeMsg codeMsg){
        return new ResultDTO<T>(codeMsg);
    }

    public static <T> ResultDTO<T> data(CodeMsg cm,T data){return new ResultDTO<T>(cm,data);}
}
