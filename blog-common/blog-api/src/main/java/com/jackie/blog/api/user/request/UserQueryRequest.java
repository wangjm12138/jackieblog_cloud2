package com.jackie.blog.api.user.request;

import com.jackie.blog.api.user.request.condition.UserAccountPasswordQueryCondition;
import com.jackie.blog.api.user.request.condition.UserQueryCondition;
import com.jackie.blog.api.user.request.condition.UserIdQueryCondition;
import com.jackie.blog.base.request.BaseRequest;
import lombok.*;

/**
 * @author jackie wang
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryRequest extends BaseRequest {
    private UserQueryCondition userQueryCondition;


    public UserQueryRequest(Long userId) {
        UserIdQueryCondition userIdQueryCondition = new UserIdQueryCondition();
        userIdQueryCondition.setUserId(userId);
        this.userQueryCondition = userIdQueryCondition;
    }

    public UserQueryRequest(String account, String password) {
        UserAccountPasswordQueryCondition userPhoneAndPasswordQueryCondition = new UserAccountPasswordQueryCondition();
        userPhoneAndPasswordQueryCondition.setAccount(account);
        userPhoneAndPasswordQueryCondition.setPassword(password);
        this.userQueryCondition = userPhoneAndPasswordQueryCondition;
    }
}
