package com.springframework.domain.base;

import com.springframework.enums.http.HttpStatus;

/**
 * @author summer
 * 2019/5/21
 */
public class RestResultBuilder<T> {
    private int code;
    private String message;
    private HttpStatus httpStatus;
    private T data;

    public RestResultBuilder() {
    }

    public RestResultBuilder<T> ofCode(int code) {
        this.code = code;
        return this;
    }

    public RestResultBuilder<T> ofMessage(String message) {
        this.message = message;
        return this;
    }

    public RestResultBuilder<T> ofHttpStatus(HttpStatus httpStatus) {
        this.message = httpStatus.getReasonPhrase();
        this.code = httpStatus.value();
        return this;
    }

    public RestResultBuilder<T> fail() {
        this.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return this;
    }

    public RestResultBuilder<T> fail(String message) {
        this.message = message;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return this;
    }

    public RestResultBuilder<T> ofData(HttpStatus httpStatus, T data) {
        this.message = httpStatus.getReasonPhrase();
        this.code = httpStatus.value();
        this.data = data;
        return this;
    }

    public RestResultBuilder<T> ofData(T data) {
        this.data = data;
        this.message = HttpStatus.OK.getReasonPhrase();
        this.code = HttpStatus.OK.value();
        return this;
    }

    public RestResultBuilder<T> ok(T data) {
        this.data = data;
        this.message = HttpStatus.OK.getReasonPhrase();
        this.code = HttpStatus.OK.value();
        return this;
    }

    public RestResultBuilder<T> ok(T data, String message) {
        this.data = data;
        this.message = message;
        this.code = HttpStatus.OK.value();
        return this;
    }

    public RestResult<T> build() {
        return new RestResult<>(code, message, data);
    }

    @Override
    public String toString() {
        return "RestResultBuilder(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ")";
    }
}
