package cn.eartech.framework.dto;

import cn.eartech.framework.Enum.AuthorityEnum;
import cn.eartech.framework.Enum.SexEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author shanfa
 * @Desc   json序列化器
 * @date 2020/3/26
 * @Version 1.0
 */
public class Serializer {}

/**
 * SexEnum序列化器
 */
class SexJsonSerializer extends JsonSerializer<SexEnum> {

    @Override
    public void serialize(SexEnum sexEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(sexEnum.getValue());

    }
}

/**
 * AuthorityEnum序列化器
 */
class AuthorityJsonSerializer extends JsonSerializer<AuthorityEnum> {

    @Override
    public void serialize(AuthorityEnum authorityEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(authorityEnum.getValue());
    }
}