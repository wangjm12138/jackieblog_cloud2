package com.jackie.blog.api.user.request;

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

    private String account;

    private String password;

}
