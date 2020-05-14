package cn.eartech.framework.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
/**
 * @author shanfa
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements IEnum<String>{
    Unknown("0","未知"),
    Male("1","男"),
    Female("2","女");

    private String value;

    private String desc;

    SexEnum(final String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static SexEnum fromValue(String value) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (value.equals(sexEnum.getValue())) {
                return sexEnum;
            }
        }
        return Unknown;
        //throw new IllegalArgumentException();
    }

    public static SexEnum fromDesc(String desc) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (desc.equals(sexEnum.toString())) {
                return sexEnum;
            }
        }
        return Unknown;
        //throw new IllegalArgumentException();
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

