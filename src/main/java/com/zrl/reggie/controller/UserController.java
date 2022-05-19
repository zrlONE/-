package com.zrl.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zrl.reggie.common.R;
import com.zrl.reggie.entity.User;
import com.zrl.reggie.service.UserService;
import com.zrl.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: zrl
 * @date: 2022/5/15 2:15
 * @description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    private String key = "zrl";

    @PostMapping("/sendMsg")
    public R<String> SendMsg(@RequestBody User user, HttpSession session){

        String phone = user.getPhone();

        if(StringUtils.isNotEmpty(phone)){
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code:{}",code);
//          SMSUtils.sendMessage();
            //将生成的验证码保存到session中
//            session.setAttribute(phone,code);


            //将生成的验证码保存到redis中
            redisTemplate.opsForValue().set(key+phone,code,5, TimeUnit.MINUTES);

            return R.success("验证码发送成功");
        }

        return R.error("验证码发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从Session中获取保存的验证码
        Object codeInRedis = redisTemplate.opsForValue().get(key+phone);
        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对)
        if(codeInRedis!=null&&code.equals(code)){
            //如果能够比对成功，说明登录成功
            //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(lambdaQueryWrapper);
            if (user==null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());

            redisTemplate.delete(key+phone);
            return R.success(user);
        }

        return R.error("登陆失败");
    }
}
