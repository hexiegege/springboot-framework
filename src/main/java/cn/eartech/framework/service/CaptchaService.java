package cn.eartech.framework.service;

/**
 * 验证码Service
 * @author shanfa
 */
public interface CaptchaService {
    /**
     * 获取验证码图片byte[]
     *
     * @param key 键
     * @return 验证码图片byte[]
     */
    byte[] getCaptchaImageBytes(String key) throws Exception;

    /**
     * 检验输入是否和验证码一致
     *
     * @param input 输入
     * @param key   key
     * @return 一致性
     */
    boolean checkCaptcha(String input, String key);
}
