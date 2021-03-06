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
package com.springframework.mvc.util;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 *
 * @param <E>
 * @author summer
 */
@Data
public class Page<E> implements Serializable {
    static final long serialVersionUID = -1L;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 页数
     */
    private int pageNumber;
    /**
     * 总页数
     */
    private int pagesAvailable;
    /**
     * 该页内容
     */
    private List<E> pageItems = new ArrayList<E>();

}
