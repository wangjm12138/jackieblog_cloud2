package com.jackie.blog.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackie.blog.user.entity.UserRole;
import com.jackie.blog.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceMapper extends BaseMapper<User> {
    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User selectById(long id);

    User selectByPhone(String phone);
    User selectByEmail(String email);
    User selectByUsername(String username);

    User loadUserByUsername(String username);

    List<UserRole> getUserRolesById(Long id);
}
