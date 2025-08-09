package com.jackie.blog.user.facade;

import com.jackie.blog.api.user.request.UserQueryRequest;
import com.jackie.blog.api.user.request.UserRegisterRequest;
import com.jackie.blog.api.user.request.condition.UserAccountPasswordQueryCondition;
import com.jackie.blog.api.user.request.condition.UserIdQueryCondition;
import com.jackie.blog.api.user.response.UserOperatorResponse;
import com.jackie.blog.api.user.service.UserFacadeService;
import com.jackie.blog.rpc.facade.Facade;
import com.jackie.blog.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hollis
 */
@DubboService(version = "1.0.0")
public class UserFacadeServiceImpl implements UserFacadeService {

    @Autowired
    private UserService userService;

    @Override
    @Facade
    public UserOperatorResponse register(UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest.getUsername(), userRegisterRequest.getPassword());
    }

    @Override
    @Facade
    public UserOperatorResponse query(UserQueryRequest userQueryRequest) {
        UserOperatorResponse userOperatorResponse = switch (userQueryRequest.getUserQueryCondition()) {
            case UserIdQueryCondition userIdQueryCondition -> userService.findUserIdWrap(userIdQueryCondition.getUserId());
            case UserAccountPasswordQueryCondition userAccountPasswordQueryCondition ->
                    userService.findAccountPassWrap(userAccountPasswordQueryCondition.getAccount(),userAccountPasswordQueryCondition.getPassword());
            default -> throw new UnsupportedOperationException("Unexpected value: " + userQueryRequest.getUserQueryCondition());
        };
        return userOperatorResponse;
    }

//    @Override
////    @Facade
//    public UserOperatorResponse test() {
//        System.out.println(111111111);
//        userService.test();
//        return null;
//    }

}
