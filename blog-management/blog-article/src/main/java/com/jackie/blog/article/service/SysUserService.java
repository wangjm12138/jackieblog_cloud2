package com.jackie.blog.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jackie.blog.article.entity.SysUser;
import com.jackie.blog.article.dao.SysUserServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @Author:  Jackie Wang
 * @WechatId:  ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2023年03月18日 20:26
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserServiceMapper sysUserServiceMapper;

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        SysUser sysUser = sysUserServiceMapper.loadUserByUsername(username);
//
//        if(sysUser == null) {
//            throw new UsernameNotFoundException("用户不存在!");
//        }
//        sysUser.setRoles(sysUserServiceMapper.getUserRolesById(sysUser.getId()));
//
//
//        return sysUser;
//    }


    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserServiceMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("anonymity");
        }
        return sysUser;
    }

//    public SysUser findUserByUsername(String Username) {
//        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysUser::getUsername,Username);
//        queryWrapper.last("limit 1");
//        return this.sysUserServiceMapper.selectOne(queryWrapper);
//    }

    public SysUser findUserByEmail(String email) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getEmail,email);
        queryWrapper.last("limit 1");
        return this.sysUserServiceMapper.selectOne(queryWrapper);
    }

    public void save(SysUser sysUser) {
        //保存用户这 id会自动生成
        //这个地方 默认生成的id是 分布式id 雪花算法
        //mybatis-plus
        this.sysUserServiceMapper.insert(sysUser);
    }
}
