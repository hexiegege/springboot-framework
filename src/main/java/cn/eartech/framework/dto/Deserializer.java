package cn.eartech.framework.dto;

import cn.eartech.framework.Enum.AuthorityEnum;
import cn.eartech.framework.Enum.SexEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/**
 * @author shanfa
 * @Desc   json反序列化器
 * @date 2020/3/26
 * @Version 1.0
 */
public class Deserializer {}
/**
 * SexEnum反序列化器
 */
class SexDeserializer extends JsonDeserializer<SexEnum> {

    @Override
    public SexEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if(jsonParser!=null){

            return SexEnum.fromValue(jsonParser.getText());
        }
        return null;
    }


}
/**
 * AuthorityEnum反序列化器
 */
class AuthorityDeserializer  extends JsonDeserializer<AuthorityEnum> {

    @Override
    public AuthorityEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if(jsonParser!=null){
            return AuthorityEnum.fromValue(jsonParser.getText());
        }
        return null;
    }


}
