package cn.eartech.framework.security;

import cn.eartech.framework.configuer.ApplicationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author shanfa
 */
@Repository
public class AuthorizationHeaderBlacklistServiceImpl implements AuthorizationHeaderBlacklistService {

    private final ApplicationConfigurer applicationConfigurer;

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    public AuthorizationHeaderBlacklistServiceImpl(ApplicationConfigurer applicationConfigurer) {
        this.applicationConfigurer = applicationConfigurer;
    }

    @Override
    public void putInInBlacklist(String authorizationHeaderValue) {
        SetOperations<String, String> set = template.opsForSet();
        set.add(applicationConfigurer.getJwtBlacklist().getSetName(), authorizationHeaderValue);
    }


    @Override
    public Boolean isBlacklistContain(String authorizationHeaderValue) {
        SetOperations<String, String> set = template.opsForSet();
        return set.isMember(applicationConfigurer.getJwtBlacklist().getSetName(), authorizationHeaderValue);
    }

}
