package com.jackie.blog.api.user.response.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 简单的用户信息，只返回部分字段，避免过多不该返回的信息被返回
 *
 * @author jackie wang
 */
@Getter
@Setter
@NoArgsConstructor
public class BasicUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 注册时间
     */
    private Date createDate;

    /**
     * 上次登录时间
     */
    private Date lastLogin;

}
