package com.jackie.blog.api.notice.service;

import com.jackie.blog.api.notice.response.NoticeResponse;

/**
 * @author jackie wang
 */
public interface NoticeFacadeService {
    /**
     * 生成并发送短信验证码
     *
     * @param telephone
     * @return
     */
    public NoticeResponse generateAndSendSmsCaptcha(String telephone);
}