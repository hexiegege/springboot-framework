package cn.eartech.framework.Enum;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author shanfa
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorityEnum implements IEnum<String> {
    Boss("boss","超级管理员"),
    Administrator("admin","管理员"),
    User("user","普通用户");

    private String value;

    private String desc;

    AuthorityEnum(final String value,String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AuthorityEnum fromValue(String value) {
        for (AuthorityEnum authorityEnum : AuthorityEnum.values()) {
            if (value.equals(authorityEnum.getValue())) {
                return authorityEnum;
            }
        }
        //return Unknown;
        throw new IllegalArgumentException();
    }

    public static AuthorityEnum fromDesc(String desc) {
        for (AuthorityEnum authorityEnum : AuthorityEnum.values()) {
            if (desc.equals(authorityEnum.toString())) {
                return authorityEnum;
            }
        }
        //return Unknown;
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return desc;
    }

    @Override
    public String getValue() {
        return value;
    }
}

