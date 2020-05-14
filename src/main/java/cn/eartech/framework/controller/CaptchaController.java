package cn.eartech.framework.controller;

import cn.eartech.framework.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码Controller
 *
 * @author shanfa
 */
@Slf4j
@Api(value = "图片验证码Controller",description = "图片验证码接口")
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    final CaptchaService captchaService;

    @Autowired
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("")
    @ApiOperation(value = "获取验证码图片", notes = "根据随机ID获取验证码图片")
    @ApiImplicitParam(name = "randomKey", value = "随机键", dataType = "String", required = true)
    public ResponseEntity<Object> getCaptcha(@RequestParam("randomKey") String randomKey) {
        try {
            byte[] captchaImageBytes = captchaService.getCaptchaImageBytes(randomKey);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    String.format("fileName=\"%s\"", "captcha.jpg"))
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(captchaImageBytes.length))
                    .header(HttpHeaders.CONNECTION, "close")
                    .body(captchaImageBytes);
        } catch (Exception e) {
            log.error("获取验证码图片失败"+e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取验证码图片失败");
        }
    }
}
