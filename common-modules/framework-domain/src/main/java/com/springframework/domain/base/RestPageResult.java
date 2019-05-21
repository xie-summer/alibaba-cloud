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

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * rest page result
 *
 * @param <T> data type
 * @author summer
 */
@Data
public class RestPageResult<T> implements Serializable {

    private static final long serialVersionUID = -8048577763828650575L;

    private int code;
    private String message;
    private int total;
    private int pageSize;
    private int currentPage;
    private T data;
    private LocalDateTime timestamp;

    RestPageResult(int code, String message, int total, int pageSize, int currentPage, T data, LocalDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.total = total;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.data = data;
        this.timestamp = timestamp;
    }

    public static <T> RestPageResultBuilder<T> builder() {
        return new RestPageResultBuilder<T>();
    }

    public static class RestPageResultBuilder<T> {
        private int code;
        private String message;
        private int total;
        private int pageSize;
        private int currentPage;
        private T data;
        private LocalDateTime timestamp;

        RestPageResultBuilder() {
        }

        public RestPageResultBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public RestPageResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public RestPageResultBuilder<T> total(int total) {
            this.total = total;
            return this;
        }

        public RestPageResultBuilder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public RestPageResultBuilder<T> currentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public RestPageResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public RestPageResultBuilder<T> timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public RestPageResult<T> build() {
            return new RestPageResult<T>(code, message, total, pageSize, currentPage, data, timestamp);
        }

        @Override
        public String toString() {
            return "RestPageResult.RestPageResultBuilder(code=" + this.code + ", message=" + this.message + ", total=" + this.total + ", pageSize=" + this.pageSize + ", currentPage=" + this.currentPage + ", data=" + this.data + ", timestamp=" + this.timestamp + ")";
        }
    }
}
