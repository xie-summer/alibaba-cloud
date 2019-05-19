/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springframework.domain.base;

import com.springframework.enums.http.HttpStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * rest result class
 *
 * @param <T> data type
 * @author summer
 */
@Data
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = 6095433538316185017L;

    private int code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public RestResult() {
    }

    public RestResult(T data) {
        this.code = HttpStatus.OK.value();
        this.setMessage(HttpStatus.OK.getReasonPhrase());
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public RestResult(HttpStatus httpStatus, T data) {
        this.code = httpStatus.value();
        this.setMessage(httpStatus.getReasonPhrase());
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public RestResult(int code, String message, T data) {
        this.code = code;
        this.setMessage(message);
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public RestResult(int code, T data) {
        this.code = code;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public RestResult(int code, String message) {
        this.code = code;
        this.setMessage(message);
        this.timestamp = LocalDateTime.now();
    }

}
