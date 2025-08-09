package com.jackie.blog.api.user.service;

import com.jackie.blog.api.user.request.*;
import com.jackie.blog.api.user.response.UserOperatorResponse;

/**
 * @author jackie wang
 */
public interface UserFacadeService {

    /**
     * 用户信息查询
     * @param userQueryRequest
     * @return UserOperatorResponse
     */
    UserOperatorResponse query(UserQueryRequest userQueryRequest);

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return UserOperatorResponse
     */
    UserOperatorResponse register(UserRegisterRequest userRegisterRequest);


    //UserOperatorResponse test();

}
