package com.jackie.blog.api.user.request.condition;


import lombok.*;

/**
 * @author jackie wang
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountPasswordQueryCondition implements UserQueryCondition {
    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号
     */
    private String account;

    /**
     * 密码
     */
    private String password;
}
