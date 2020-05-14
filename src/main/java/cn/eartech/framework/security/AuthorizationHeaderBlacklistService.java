package cn.eartech.framework.security;

/**
 * 请求头Authorization黑名单Service
 *
 * @author shanfa
 */
public interface AuthorizationHeaderBlacklistService {
    /**
     * 将HTTP Authorization头中的值添加到JWT黑名单集合
     *
     * @param authorizationHeaderValue 请求头Authorization的值
     */
    void putInInBlacklist(String authorizationHeaderValue);

    /**
     * 检查HTTP Authorization是否在JWT黑名单集合
     *
     * @param authorizationHeaderValue 请求头Authorization的值
     * @return 是否再JWT黑名单中
     */
    Boolean isBlacklistContain(String authorizationHeaderValue);
}
