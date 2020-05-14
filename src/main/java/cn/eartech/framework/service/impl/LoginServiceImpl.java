package cn.eartech.framework.service.impl;

import cn.eartech.framework.dao.UserDao;
import cn.eartech.framework.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 登录Service实现
 * @author shanfa
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;
    private final StringRedisTemplate template;

    @Autowired
    public LoginServiceImpl(UserDao userDao, StringRedisTemplate template) {
        this.userDao = userDao;
        this.template = template;
    }

    /**
     * 登录成功
     *
     * @param id 用户id
     */
    @Override
    public void successfulLogin(String id) {
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = String.format("%s_%s", id, dateString);
        String valueString = valueOperations.get(key);
        if (!StringUtils.isEmpty(valueString)) {
            template.delete(key);
        }

    }

    /**
     * 登录失败
     *
     * @param id 用户id
     * @return 当日登录失败次数
     */
    @Override
    public int failedLogin(String id) {
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = String.format("%s_%s", id, dateString);
        String valueString = valueOperations.get(key);
        if (StringUtils.isEmpty(valueString)) {
            valueOperations.set(key, String.valueOf(1), 1, TimeUnit.DAYS);
            return 1;
        } else {
            int failedTimes = Integer.parseInt(valueString) + 1;
            valueOperations.set(key, String.valueOf(failedTimes), 1, TimeUnit.DAYS);
            return failedTimes;
        }
    }

    /**
     * 获取指定角色下，某个昵称的当日登录失败次数
     *
     * @param id 用户id
     * @return 当日登录失败次数
     */
    @Override
    public int getTodayFailedLoginTimes(String id) {
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = String.format("%s_%s", id, dateString);
        String valueString = valueOperations.get(key);
        if (StringUtils.isEmpty(valueString)) {
            return 0;
        } else {
            return Integer.parseInt(valueString);
        }
    }
}
