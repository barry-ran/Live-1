package com.horen.domain.d8;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/12:44
 * @description :D8视频模块基类
 * @github :https://github.com/chenyy0708
 */
public class BaseEntry<T> {
    /**
     * data : {}
     * error : {"client":"获取成功","code":0,"server":"成功"}
     */

    private T data;
    private ErrorBean error;

    public T getData() {
        return data;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return error.code == 0;
    }

    public static class DataBean {
    }

    public static class ErrorBean {
        /**
         * client : 获取成功
         * code : 0
         * server : 成功
         */

        private String client;
        private int code;
        private String server;

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }
    }
}
