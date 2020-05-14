package cn.eartech.framework.service.impl;

import cn.eartech.framework.configuer.ApplicationConfigurer;
import cn.eartech.framework.service.CaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * CaptchaService实现
 *
 * @author shanfa
 */
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private final ApplicationConfigurer applicationConfigurer;

    private final DefaultKaptcha defaultKaptcha;

    private final StringRedisTemplate template;

    @Autowired
    public CaptchaServiceImpl(ApplicationConfigurer applicationConfigurer, DefaultKaptcha defaultKaptcha, StringRedisTemplate template) {
        this.applicationConfigurer = applicationConfigurer;
        this.defaultKaptcha = defaultKaptcha;
        this.template = template;
    }

    @Override
    public byte[] getCaptchaImageBytes(String key) throws Exception {
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String captchaText = defaultKaptcha.createText();
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(String.format("%s%s", applicationConfigurer.getCaptcha().getPrefix(), key), captchaText, applicationConfigurer.getCaptcha().getLifeTimeMinute(), TimeUnit.MINUTES);
        BufferedImage challenge = defaultKaptcha.createImage(captchaText);
        try {
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IOException e) {
            log.error("获取验证码字节数组失败"+e.toString());
            throw new Exception("获取验证码字节数组失败");
        }
        return jpegOutputStream.toByteArray();
    }

    @Override
    public boolean checkCaptcha(String input, String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        String captcha = ops.get(String.format("%s%s", applicationConfigurer.getCaptcha().getPrefix(), key));
        if (StringUtils.isEmpty(captcha)) {
            return false;
        }
        return input.equals(captcha);
    }
}
