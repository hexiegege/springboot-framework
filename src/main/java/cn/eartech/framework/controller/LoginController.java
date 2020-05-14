package cn.eartech.framework.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shanfa
 * @Desc
 * @date 2020/3/24-0:43
 */
@RestController
@Api(value = "登录接口",description = "获取登录授权")
public class LoginController {

    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo",value ="手机号"),
            @ApiImplicitParam(name = "password",value ="密码"),
    })
    ResponseEntity<Object> login(@RequestParam("phoneNo")String phoneNo,@RequestParam("password")String password){
        return ResponseEntity.ok().build();
     }
}
