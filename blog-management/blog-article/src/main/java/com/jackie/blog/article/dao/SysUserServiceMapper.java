package com.jackie.blog.article.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackie.blog.article.entity.SysRole;
import com.jackie.blog.article.entity.SysUser;

import java.util.List;

public interface SysUserServiceMapper extends BaseMapper<SysUser> {

//    void insertUser(SysUser user);
    SysUser loadUserByUsername(String username);

    List<SysRole> getUserRolesById(Long id);
}
