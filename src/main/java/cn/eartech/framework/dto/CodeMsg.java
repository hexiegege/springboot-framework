package cn.eartech.framework.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 返回码 0表示成功 非0表示失败
 * @author shanfa
 */

@Getter
@Setter
@ToString
public class CodeMsg {
    private int code;
    private String msg;

    //错误码
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg ERROR = new CodeMsg(-1,"error");
    public static CodeMsg BAD_REQUEST = new CodeMsg(400,"请求的数据格式不符!");
    public static CodeMsg FORBIDDEN = new CodeMsg(403,"权限不足,请先登录!");
    public static CodeMsg NOT_FOUND = new CodeMsg(404, "未找到该资源!");
    public static CodeMsg REQUEST_TIMEOUT = new CodeMsg(408, "请求超时");
    public static CodeMsg INTERNAL_SERVER_ERROR = new CodeMsg(500, "服务器内部错误!");
    public static CodeMsg BAD_GATEWAY = new CodeMsg(502, "网关错误!");
    public static CodeMsg SERVICE_UNAVAILABLE = new CodeMsg(503, "服务不可用!");
    public static CodeMsg GATEWAY_TIMEOUT = new CodeMsg(504, "网关超时!");
    public static CodeMsg SERVER_BUSY = new CodeMsg(512,"服务器正忙，请稍后再试!");
    public static CodeMsg INSERT_ERROR = new CodeMsg(100001,"插入失败!");
    public static CodeMsg SELECT_ERROR = new CodeMsg(100002,"查询失败!");
    public static CodeMsg UPDATE_ERROR = new CodeMsg(100003,"更新失败!");
    public static CodeMsg DELETE_ERROR = new CodeMsg(100004,"删除失败!");
    public static CodeMsg NULL_POINT = new CodeMsg(200001,"数据格式不符，空指针异常!");

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
