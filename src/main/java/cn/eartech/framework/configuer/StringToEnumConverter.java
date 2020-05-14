package cn.eartech.framework.configuer;

import cn.eartech.framework.Enum.AuthorityEnum;
import cn.eartech.framework.Enum.SexEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * @author shanfa
 * @Desc  http请求参数 转换为枚举
 * @date 2020/3/26
 * @Version 1.0
 */
public class StringToEnumConverter {

}

class StringToSexEnumConverter implements Converter<String, SexEnum> {
    @Nullable
    @Override
    public SexEnum convert(String value) {
        return SexEnum.fromValue(value);
    }
}

class StringToAuthorityEnumConverter implements Converter<String, AuthorityEnum> {
    @Nullable
    @Override
    public AuthorityEnum convert(String value) {
        return AuthorityEnum.fromValue(value);
    }
}