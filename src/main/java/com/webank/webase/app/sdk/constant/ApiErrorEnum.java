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
package com.webank.webase.app.sdk.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * ApiErrorEnum.
 */
@Getter
@ToString
@AllArgsConstructor
public enum ApiErrorEnum {

    HTTP_REQUEST_ERROR(1_05_001,"Http request error."),

    PROPERTY_BLANK_ERROR(2_05_101,"Init app config error, property blank."),
    CONFIG_INIT_ERROR(2_05_102,"Init app config error."),
    CONFIG_NOT_INITIALIZED(2_05_103,"Please init AppConfig first before send any request."),
    ;

    private int code;
    private String format;

    /**
     * format.
     * 
     * @param args
     * @return
     */
    public String format(Object... args) {
        try {
            return String.format(this.format, args);
        } catch (Exception e) {
            return String.format("%s:%s", this.format, StringUtils.join(args, ","));
        }
    }

}
