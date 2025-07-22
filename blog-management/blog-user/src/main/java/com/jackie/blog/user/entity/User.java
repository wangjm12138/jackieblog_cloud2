package com.jackie.blog.user.entity;

import com.jackie.blog.datasource.domain.entity.BaseEntity;
import lombok.Data;


/**
 * @作者 Jackie Wang
 * @微信号 ilovepython12138
 * @GitHub https://github.com/wangjm12138
 * @博客 http://www.jackieblog.top
 * @date 2023年03月18日 20:28
 */

@Data
public class User extends BaseEntity {

    private String username;

    private String password;

    private Boolean enabled;

    private String avatar;

    private Long createDate;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private Integer gender;
}
