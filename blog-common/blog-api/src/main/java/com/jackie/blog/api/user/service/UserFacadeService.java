package com.jackie.blog.api.user.service;

import com.jackie.blog.api.user.request.*;
import com.jackie.blog.api.user.response.UserOperatorResponse;

/**
 * @author jackie wang
 */
public interface UserFacadeService {

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    UserOperatorResponse register(UserRegisterRequest userRegisterRequest);
}
