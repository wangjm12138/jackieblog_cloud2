package com.jackie.blog.user.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.jackie.blog.api.user.response.UserOperatorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {


    @Transactional
    public UserOperatorResponse register(String telephone, String inviteCode) {

        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();
        userOperatorResponse.setSuccess(true);

        return userOperatorResponse;
    }

}
