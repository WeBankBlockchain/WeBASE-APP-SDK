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
package com.webank.webase.app.sdk.exception;

import com.webank.webase.app.sdk.constant.ApiErrorEnum;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;

/**
 * ApiException.
 */
@Getter
@ToString
public class ApiException extends RuntimeException {
    
    private static final long serialVersionUID = 2325082102878230423L;
    
    private int code;
    private String message;

    public ApiException(ApiErrorEnum resultEnum, Object ... paramArray){
        this.code = resultEnum.getCode();
        if (ArrayUtils.isNotEmpty(paramArray)){
            this.message = resultEnum.format(paramArray);
        }else{
            this.message = resultEnum.getFormat();
        }
    }
    
    public ApiException(int code, String message){
        this.code = code;
        this.message = message;
    }
}
