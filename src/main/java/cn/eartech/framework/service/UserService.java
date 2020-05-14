package cn.eartech.framework.service;

import cn.eartech.framework.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shanfa
 * @since 2020-03-22
 */
public interface UserService extends IService<User> {

    List<User> selectByName(String name);

    int saveNewUser(User user);

    boolean checkPassword(User user, String password);

    void testError();
    void testError1();
}
