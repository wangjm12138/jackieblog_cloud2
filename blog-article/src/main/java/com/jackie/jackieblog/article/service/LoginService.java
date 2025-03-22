package com.jackie.jackieblog.article.service;

import com.jackie.jackieblog.article.entity.RegisterData;
import com.jackie.jackieblog.article.entity.SysUser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: Jackie Wang
 * @WechatID: ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年07月23日 8:41
 * @Description:
 */
@Service
public class LoginService {

    @Autowired
    private SysUserService sysUserService;

    private static final String slat = "jackieblog!@#";

//    public Result registerByAccount(RegisterData registerData){
//        String username = registerData.getUsername();
//        String password = registerData.getPassword();
//        if (StringUtils.isBlank(username)
//                || StringUtils.isBlank(password)
//        ){
//            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
//        }
//        SysUser sysUser = sysUserService.findUserByUsername(username);
//        if (sysUser != null){
//            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), "账户已经注册过了");
//        }
//        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
//
//        sysUser = new SysUser();
//        sysUser.setUsername(username);
//        sysUser.setPassword(encoder.encode(password));
//        sysUser.setEnabled(Boolean.TRUE);
//        sysUser.setLocked(Boolean.FALSE);
//        sysUser.setAvatar("");
//        sysUser.setCreateDate(System.currentTimeMillis());
//        sysUser.setDeleted(0);
//        sysUser.setEmail("");
//        sysUser.setLastLogin(System.currentTimeMillis());
//        sysUser.setMobilePhoneNumber("");
//        sysUser.setNickname("");
//        this.sysUserService.save(sysUser);
//
//        return Result.success("创建成功");
//    }
}
