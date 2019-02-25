package com.apollo.common;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/22
 */
public class ApiResult {
    public static final Integer STATUS_SUCCESS = 0;
    public static final Integer STATUS_FAILURE = -1;

    public static final String DESC_SUCCESS = "操作成功";
    public static final String DESC_FAILURE = "操作失败";

    private Integer status;
    private String desc;
    private Object result;

    private ApiResult() {}

    private ApiResult(Integer status, String desc, Object result) {
        this.status = status;
        this.desc = desc;
        this.result = result;
    }

    public static ApiResult success(Object result) {
        return success(DESC_SUCCESS, result);
    }

    public static ApiResult success(String desc, Object result) {
        return new ApiResult(STATUS_SUCCESS, desc, result);
    }

    public static ApiResult failure(Integer status) {
        return failure(status, null);
    }

    public static ApiResult failure(Integer status, String desc) {
        return failure(status, desc, null);
    }

    public static ApiResult failure(Integer status, String desc, Object result) {
        return new ApiResult(status, desc, result);
    }

    public static Builder builder() {
        return new Builder();
    }

    //静态内部类，这里使用Builder设计模式
    public static class Builder {
        private Integer status;
        private String desc;
        private Object result;

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder result(Object result) {
            this.result = result;
            return this;
        }

        public ApiResult build() {
            return new ApiResult(status, desc, result);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
