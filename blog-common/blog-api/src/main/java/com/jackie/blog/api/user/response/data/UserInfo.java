package com.jackie.blog.api.user.response.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author jackie wang
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfo extends BasicUserInfo {


    /**
     * 用户角色
     */
//    private UserRole userRole;

    /**
     * 邀请码
     */
    private String inviteCode;

}
