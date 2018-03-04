package com.jokers.pojo.bo;

import java.util.List;

/**
 * <p>GPErrorResultBo class.</p>
 *
 * @author yuton
 * @version 1.0
 *  谷歌位置错误信息返回
 * @since 2017/4/20 19:07
 */
public class GPErrorResultBo {

    /**
     * error : {"errors":[{"domain":"global","reason":"parseError","message":"Parse Error"}],"code":400,"message":"Parse Error"}
     */

    private ErrorBean error;

    /**
     * <p>Getter for the field <code>error</code>.</p>
     *
     * @return a {@link com.jokers.pojo.bo.GPErrorResultBo.ErrorBean} object.
     */
    public ErrorBean getError() {
        return error;
    }

    /**
     * <p>Setter for the field <code>error</code>.</p>
     *
     * @param error a {@link com.jokers.pojo.bo.GPErrorResultBo.ErrorBean} object.
     */
    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class ErrorBean {
        /**
         * errors : [{"domain":"global","reason":"parseError","message":"Parse Error"}]
         * code : 400
         * message : Parse Error
         */

        private int code;
        private String message;
        private List<ErrorsBean> errors;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ErrorsBean> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorsBean> errors) {
            this.errors = errors;
        }

        public static class ErrorsBean {
            /**
             * domain : global
             * reason : parseError
             * message : Parse Error
             */

            private String domain;
            private String reason;
            private String message;

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}
