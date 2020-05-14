package cn.eartech.framework.addition;

import cn.eartech.framework.Enum.AuthorityEnum;
import cn.eartech.framework.Enum.SexEnum;
import cn.eartech.framework.FrameworkApplication;
import cn.eartech.framework.dto.UserDTO;
import cn.eartech.framework.entity.User;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkApplication.class)
public class Test {


    public static void main(String[] args) {

    }
    @Autowired
    Mapper mapper;

    @org.junit.Test
    public void Test(){
        User user = new User();
        user.setName("name");
        user.setPhoneNo("15656522546");
        user.setSex(SexEnum.Female);
        user.setAuthority(AuthorityEnum.Administrator);
        user.setId("1");
        user.setPassword("2345235");
        //user.setBirthday(LocalDate.of(1992,5,15));
        UserDTO userDTO = mapper.map(user,UserDTO.class);
        System.out.println(userDTO.getAuthority().name());
        System.out.println(userDTO.getBirthday());

        User user1 = mapper.map(userDTO,User.class);
        System.out.println(user1.getPassword());
    }

}
