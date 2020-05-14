package cn.eartech.framework.service;

/**
 * 登录Service
 *
 * @author 谷鑫
 */
public interface LoginService {
    /**
     * 登录成功
     *
     * @param id
     */
    void successfulLogin(String id);

    /**
     * 登录失败
     *
     * @param id
     * @return 当日登录失败次数
     */

    int failedLogin(String id);

    /**
     * 获取指定角色下，某个昵称的当日登录失败次数
     *
     * @param id
     * @return 当日登录失败次数
     */
    int getTodayFailedLoginTimes(String id);
}
