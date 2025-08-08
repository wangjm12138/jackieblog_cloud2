package com.jackie.blog.api.notice.response;

import com.jackie.blog.base.response.BaseResponse;
/**
 * @author jackie wang
 */
public class NoticeResponse extends BaseResponse {

    public static class Builder {
        private NoticeResponse response;
        public Builder() {
            response = new NoticeResponse();
        }
        public Builder setCode(String code) {
            response.setResponseCode(code);
            return this;
        }
        public Builder setMessage(String message) {
            response.setResponseMessage(message);
            return this;
        }
        public Builder setSuccess(boolean success) {
            response.setSuccess(success);
            return this;
        }
        public NoticeResponse build() {
            return response;
        }
    }
}