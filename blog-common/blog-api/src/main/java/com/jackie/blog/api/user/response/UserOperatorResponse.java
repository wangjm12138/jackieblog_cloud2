package com.jackie.blog.api.user.response;

import com.jackie.blog.api.user.response.data.UserInfo;
import com.jackie.blog.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户操作响应
 *
 * @author jackie wang
 */
@Getter
@Setter
public class UserOperatorResponse extends BaseResponse {
    private UserInfo user;
}
