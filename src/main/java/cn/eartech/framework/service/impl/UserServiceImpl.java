package cn.eartech.framework.service.impl;

import cn.eartech.framework.entity.User;
import cn.eartech.framework.dao.UserDao;
import cn.eartech.framework.exception.CommonException;
import cn.eartech.framework.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shanfa
 * @since 2020-03-22
 */
@Service(value = "UserService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService,UserDetailsService{


    @Autowired
    UserDao userDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<User> selectByName(String name) {
        return userDao.selectByName(name);
    }

    @Override
    public int saveNewUser(User user) {
        if(!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        int result = userDao.insert(user);
        return result;
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = new User();
        user.setName(name);
        QueryWrapper queryWrapper = new QueryWrapper<>().setEntity(user);
        User user1 = userDao.selectOne(queryWrapper);
        if (user1 == null) {
            throw new UsernameNotFoundException(name);
        }
        return new org.springframework.security.core.userdetails.User(user1.getId(), user1.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user1.getAuthority().getValue())));

    }

    @Override
    public void testError() {
        log.error("用户姓名不能为空！");
        throw  new CommonException(-1,"用户姓名不能为空！");
    }

    @Override
    public void testError1() {
        Integer.parseInt("abc56456");
    }
}
