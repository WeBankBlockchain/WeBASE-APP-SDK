/**
 * Copyright 2014-2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.webase.app.sdk.dto.rsp.base;

import com.webank.webase.app.sdk.exception.ApiException;
import com.webank.webase.app.sdk.util.JacksonUtil;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

@Data
@NoArgsConstructor
@ToString
public class Response {
    private int code;
    private String message = "";
    private Object data;
    private long totalCount;

    public static <T> T toObject(String json, Class<T> clazz) {
        Response response = checkResponse(json);
        T result = JacksonUtil.toJavaObject(response.getData(), clazz);
        return result;
    }
    
    public static <T> List<T> toList(String json, Class<T> clazz) {
        Response response = checkResponse(json);
        List<T> result = Collections.emptyList();
        if (response.totalCount > 0) {
            result = JacksonUtil.toJavaObjectList(response.getData(), clazz);
        }
        return result;
    }

    public static <T> List<T> toListWithoutCount(String json, Class<T> clazz) {
        Response response = checkResponse(json);
        List<T> result = Collections.emptyList();
        result = JacksonUtil.toJavaObjectList(response.getData(), clazz);
        return result;
    }

    public static <T> Pair<Long, List<T>> toListAndCount(String json, Class<T> clazz) {
        Response response = checkResponse(json);
        List<T> result = Collections.emptyList();
        if (response.totalCount > 0) {
            result = JacksonUtil.toJavaObjectList(response.getData(), clazz);
        }
        return Pair.of(response.totalCount, result);
    }


    public static Response checkResponse(String json) {
        Response response = JacksonUtil.toJavaObject(json, Response.class);
        if (response == null || response.failed()) {
            throw new ApiException(response.getCode(), response.getMessage());
        }
        return response;
    }

    private boolean failed() {
        return code != 0;
    }
}
