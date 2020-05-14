package cn.eartech.framework.configuer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统配置，读取自配置文件application节
 * @author shanfa
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationConfigurer {

    /**
     * 配置文件application.we-chat-mp节
     */
    private WeChatMp weChatMp;

    /**
     * 配置文件application.authentication节
     * 构建JWT时的签名信息
     */
    private Authentication authentication;

    /**
     * 配置文件application.jwt-blacklist节
     * token的黑名单
     */
    private JwtBlacklist jwtBlacklist;

    /**
     *  配置文件application.need-reLogin-days节
     *   14天未登录，则需要重新登陆
     */
    private int needReLoginDays;

    /**
     * 配置文件application.failed-login节
     * 配置最大错误登录次数
     */
    private FailedLogin failedLogin;

    /**
     * 配置文件application.default-password节
     * 默认密码
     */
    private String defaultPassword;

    /**
     * 配置文件application.role-name节
     * 权限名称
     */
    private RoleName roleName;

    /**
     * 配置文件application.avatar节
     * 头像文件配置信息
     */
    private Avatar avatar;
    /**
     * 配置文件application.captcha节
     * 验证码配置信息
     */
    private Captcha captcha;
    /**
     * 配置文件application.sms-verification-code节
     * 短信模板配置信息
     */
    private SmsVerificationCode smsVerificationCode;

    /**
     * 配置文件application.base-url节
     * 根路径localhost+tomcat端口
     */
    private String baseUrl;

    /**
     * 配置文件application.default-service-staff-id节点
     * 默认的客服id
     */
    private String defaultServiceStaffId;

    /**
     * 百度云推送的
     */
    private BaiDuYunPush baiduYunPush;

    @Getter
    @Setter
    public static class BaiDuYunPush {

        private String apiKey;

        private String secretKey;
    }

    @Getter
    @Setter
    public static class JwtBlacklist {
        /**
         * 配置文件application.jwt-blacklist.life-time-hour节
         */
        private int lifeTimeHour;

        /**
         * 配置文件application.jwt-blacklist.set-name的值
         */
        private String setName;
    }


    @Getter
    @Setter
    public static class Authentication {
        /**
         * 配置文件application.authentication.key的值
         */
        private String key;
    }

    @Getter
    @Setter
    public static class FailedLogin {
        /**
         * 配置文件application.failed-login.max-times-per-date节
         */
        private int maxTimesPerDate;
    }

    @Getter
    @Setter
    public static class RoleName {
        /**
         * 配置文件application.role-name.boss节
         */
        private String boss;
        /**
         * 配置文件application.role-name.admin-user节
         */
        private String adminUser;
        /**
         * 配置文件application.role-name.patient节
         */
        private String patient;
        /**
         * 配置文件application.role-name.relative节
         */
        private String serviceStaff;
        /**
         * 配置文件application.role-name.service-staff节
         */
        private String relative;
        /**
         * 配置文件application.role-name.doctor节
         */
        private String doctor;
    }

    @Getter
    @Setter
    public static class Avatar {
        /**
         * 配置文件application.avatar.outputFormat的值
         */
        private String outputFormat;
        /**
         * 配置文件application.avatar.outputWidth的值
         */
        private int outputWidth;
        /**
         * 配置文件application.avatar.outputHeight的值
         */
        private int outputHeight;
    }


    @Getter
    @Setter
    public static class Captcha {
        /**
         * 配置文件application.captcha.key的值
         */
        private String prefix;
        /**
         * 配置文件application.captcha.life-time-minute的值
         */
        private int lifeTimeMinute;
    }

    @Getter
    @Setter
    public static class SmsVerificationCode {
        /**
         * 配置文件application.sms-verification-code.login-prefix的值
         */
        private String loginPrefix;
        /**
         * 配置文件application.sms-verification-code.register-prefix的值
         */
        private String registerPrefix;

        /**
         * 配置文件application.sms-verification-code.life-time-minute的值
         */
        private int lifeTimeMinute;
        /**
         * 配置文件application.sms-verification-code. access-key-id的值
         */
        private String accessKeyId;
        /**
         * 配置文件application.sms-verification-code.access-key-secret的值
         */
        private String accessKeySecret;
        /**
         * 配置文件application.sms-verification-code.sign-name的值
         */
        private String signName;
        /**
         * 配置文件application.sms-verification-code.login-template-code的值
         */
        private String loginTemplateCode;
        /**
         * 配置文件application.sms-verification-code.login-template-param的值
         */
        private String loginTemplateParam;
        /**
         * 配置文件application.sms-verification-code.register-template-code的值
         */
        private String registerTemplateCode;
        /**
         * 配置文件application.sms-verification-code.register-template-param的值
         */
        private String registerTemplateParam;
        /**
         * 配置文件application.sms-verification-code.reset-password-prefix的值
         */
        private String resetPasswordPrefix;
        /**
         * 配置文件application.sms-verification-code.reset-password-template-code的值
         */
        private String resetPasswordTemplateCode;
        /**
         * 配置文件application.sms-verification-code.reset-password-template-param的值
         */
        private String resetPasswordTemplateParam;
        /**
         * 配置文件application.sms-verification-code.bind-cell-phone-no-prefix的值
         */
        private String bindCellPhoneNoPrefix;
        /**
         * 配置文件application.sms-verification-code.bind-cell-phone-no-template-code的值
         */
        private String bindCellPhoneNoTemplateCode;
        /**
         * 配置文件application.sms-verification-code.bind-cell-phone-no-template-param的值
         */
        private String bindCellPhoneNoTemplateParam;
    }

    @Getter
    @Setter
    public static class WeChatMp {
        /**
         * 配置文件application.wechat-mp.app-id的值
         */
        private String appId;
        /**
         * 配置文件application.wechat-mp.secret的值
         */
        private String secret;
        /**
         * 配置文件application.wechat-mp.token的值
         */
        private String token;
        /**
         * 配置文件application.wechat-mp.aes-key的值
         */
        private String aesKey;
    }
}
