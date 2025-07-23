package com.jackie.blog.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackie.blog.user.entity.SysRole;
import com.jackie.blog.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceMapper extends BaseMapper<User> {

    Optional<User> selectByPhone(String phone);
    Optional<User> selectByEmail(String email);
    Optional<User> selectByUsername(String username);

    User loadUserByUsername(String username);

    List<SysRole> getUserRolesById(Long id);
}
