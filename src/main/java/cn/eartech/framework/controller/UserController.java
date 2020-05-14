package cn.eartech.framework.controller;


import cn.eartech.framework.Enum.SexEnum;
import cn.eartech.framework.common.DozerUtils;
import cn.eartech.framework.dto.CodeMsg;
import cn.eartech.framework.dto.ResultDTO;
import cn.eartech.framework.dto.UserDTO;
import cn.eartech.framework.entity.User;
import cn.eartech.framework.exception.CommonException;
import cn.eartech.framework.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shanfa
 * @since 2020-03-22
 */
@Slf4j
//@PreAuthorize("isAuthenticated()")//进入方法之前验证授权。可以将登录用户的roles参数传到方法中验证。
@RestController//开启控制器
@RequestMapping("/user")//基本访问路径
@Api(value = "用户接口",description = "用户接口")//开启API,在swagger-ui.html可以看到
public class UserController {
    //自动注入Service
    @Autowired
    UserService userService;

    @Autowired
    Mapper mapper;

    @GetMapping("/all")
    @ApiOperation(value = "获取全部用户", notes = "获取登录用户的资料")
    public ResponseEntity<?> getAllUsers(){
        List<User> userList = userService.list(null);
        List<UserDTO> userDTOS = DozerUtils.map(mapper, userList, UserDTO.class);
        return ResponseEntity.ok(ResultDTO.success(userDTOS));
    }


    @GetMapping("/sex")
    @ApiImplicitParam(name = "sex",value = "性别")
    public ResponseEntity<?> getBySex(@RequestParam("sex")SexEnum sexEnum){
        User user = new User();
        user.setSex(sexEnum);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(user);
        List<User> userList = userService.list(queryWrapper);
        List<UserDTO> userDTOList = DozerUtils.map(mapper,userList,UserDTO.class);
        return ResponseEntity.ok(ResultDTO.success(userDTOList));
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody UserDTO userDTO){
        try {
            userDTO.getAuthority();
            User user = mapper.map(userDTO,User.class);
            boolean result = userService.saveOrUpdate(user);
            if(result){
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.setEntity(user);
                user = userService.getOne(queryWrapper);
                return ResponseEntity.ok(ResultDTO.success(mapper.map(user,UserDTO.class)));
            } else {
                CodeMsg codeMsg =new CodeMsg(1,"创建失败");
                return ResponseEntity.ok(ResultDTO.error(codeMsg));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            CodeMsg codeMsg =new CodeMsg(1,"创建失败");
            return ResponseEntity.ok(ResultDTO.error(codeMsg));
        }
    }

    @GetMapping("/error1")
    public ResponseEntity<?> error1(String error) {
        System.out.println("测试 error1...");
        if(error.equals("1")){
            throw  new CommonException(-1,"用户姓名不能为空！");
        }
        return ResponseEntity.ok(1);
    }

    @GetMapping("/error2")
    public ResponseEntity<?> error2() {
        System.out.println("测试 error2...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str=null;
        str.equals("111");
        return ResponseEntity.ok(2);
    }

    @GetMapping("/error3")
    public ResponseEntity<?> error3()  {
        System.out.println("开始删除...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return ResponseEntity.ok(3);
    }

    @GetMapping("/error4")
    public ResponseEntity<?> error4()  {
        userService.testError();
        return ResponseEntity.ok(3);
    }

    @GetMapping("/error5")
    public ResponseEntity<?> error5()  {
        userService.testError1();
        return ResponseEntity.ok(3);
    }


}
