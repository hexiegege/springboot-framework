package cn.eartech.framework.entity;

import cn.eartech.framework.Enum.AuthorityEnum;
import cn.eartech.framework.Enum.SexEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 * 映射一下表名
 * @author shanfa
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private LocalDate birthday;

    private AuthorityEnum authority;

    private String password;

    private SexEnum sex;

    private String phoneNo;


}
