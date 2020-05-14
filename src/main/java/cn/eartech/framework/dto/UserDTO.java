package cn.eartech.framework.dto;

import cn.eartech.framework.Enum.AuthorityEnum;
import cn.eartech.framework.Enum.SexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author shanfa
 * @Desc 序列化已经实现 作为RequestBody传递参数才可以反序列化枚举
 *        作为实体类传递会报错
 * @date 2020/3/25
 * @Version 1.0
 */
@ToString
@Getter
@Setter
@ApiModel(value = "用户信息")
public class UserDTO implements Serializable{
    private String id;

    private String name;
    // 两个注解二选一 序列化和反序列化LocalDate 用 jsr-310
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "birthDate", value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(name = "authority", value = "权限")
    @JsonDeserialize(using = AuthorityDeserializer.class)//json反序列化
    @JsonSerialize(using = AuthorityJsonSerializer.class)//json序列化
    private AuthorityEnum authority;

    @ApiModelProperty(name = "sex", value = "性别")
    @JsonDeserialize(using = SexDeserializer.class)
    @JsonSerialize(using = SexJsonSerializer.class)
    private SexEnum sex;

    private String phoneNo;
}
