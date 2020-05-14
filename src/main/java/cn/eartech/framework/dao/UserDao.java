package cn.eartech.framework.dao;

import cn.eartech.framework.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author shanfa
 * @since 2020-03-22
 */
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user where name = #{name}")
    List<User> selectByName(String name);

}
