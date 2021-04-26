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
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@AllArgsConstructor
@Slf4j
public enum EncryptTypeEnum {
    ECDSA_TYPE(0,"ECDSA"),
    SM2_TYPE(1,"SM2, guomi"),
    ;

    private int type;
    private String description;


    /**
     *
     * @param type
     * @return
     */
    public static EncryptTypeEnum get(int type){
        return get(type,ECDSA_TYPE);
    }

    public static EncryptTypeEnum get(int type, EncryptTypeEnum defaultType){
        for (EncryptTypeEnum value : EncryptTypeEnum.values()) {
            if (type == value.getType()) {
                return value;
            }
        }
        log.warn("Get encrypt type error, use default:[{}].", defaultType);
        return defaultType;
    }
}
