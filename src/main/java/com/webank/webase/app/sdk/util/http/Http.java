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
package com.webank.webase.app.sdk.util.http;

import com.webank.webase.app.sdk.config.AppConfig;
import com.webank.webase.app.sdk.constant.ApiErrorEnum;
import com.webank.webase.app.sdk.constant.HttpTypeEnum;
import com.webank.webase.app.sdk.constant.SdkConstant;
import com.webank.webase.app.sdk.dto.req.base.Request;
import com.webank.webase.app.sdk.exception.ApiException;
import com.webank.webase.app.sdk.util.JacksonUtil;
import com.webank.webase.app.sdk.util.codec.Md5Util;
import java.util.HashMap;
import java.util.Map;

/**
 * A util to send common http request to webase node manager.
 * <p>
 * 1. Append common params to request url. 2. Sign request and append signature to request url.
 */
public class Http {

    /**
     * @param api
     * @return
     */
    public static String get(AppConfig config, String api) {
        return get(config, api, null);
    }

    /**
     * @param api
     * @return
     */
    public static String get(AppConfig config, String api, Map<String, Object> paramMap) {
        if (config == null) {
            throw new ApiException(ApiErrorEnum.CONFIG_NOT_INITIALIZED);
        }

        // build parameter map
        Map<String, Object> paramMapMulti = buildMapWithSign(config);
        if (paramMap != null) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                paramMapMulti.put(entry.getKey(), entry.getValue());
            }
        }

        return HttpUtil.get(config, api, paramMapMulti);
    }

    /**
     * @param api
     * @return
     */
    public static String post(AppConfig config, String api, Request requestBody) {
        if (config == null) {
            throw new ApiException(ApiErrorEnum.CONFIG_NOT_INITIALIZED);
        }

        // build parameter map
        Map<String, Object> paramMap =
                buildMapWithSign(config, JacksonUtil.toJSONString(requestBody));

        return HttpUtil.post(config, api, paramMap, requestBody);
    }

    /**
     * @param api
     * @return
     */
    public static String postFile(AppConfig config, String api, Map<String, Object> paramMap) {
        if (config == null) {
            throw new ApiException(ApiErrorEnum.CONFIG_NOT_INITIALIZED);
        }

        // build parameter map
        Map<String, Object> paramMapMulti = buildMapWithSign(config);
        if (paramMap != null) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                paramMapMulti.put(entry.getKey(), entry.getValue());
            }
        }

        return HttpUtil.post(config, api, paramMapMulti, null, HttpTypeEnum.FILE_TYPE.getType());
    }

    /**
     * @return
     */
    private static Map<String, Object> buildMapWithSign(AppConfig config) {
        Map<String, Object> paramMap = buildMapWithoutSign(config);
        String signature = Md5Util.md5Encrypt(paramMap.get(SdkConstant.PARAM_TIMESTAMP)
                + config.getAppKey() + config.getAppSecret());
        paramMap.put(SdkConstant.PARAM_SIGNATURE, signature);
        return paramMap;
    }

    /**
     * @param body
     * @return
     */
    private static Map<String, Object> buildMapWithSign(AppConfig config, String body) {
        Map<String, Object> paramMap = buildMapWithoutSign(config);
        String signature = Md5Util.md5Encrypt(paramMap.get(SdkConstant.PARAM_TIMESTAMP)
                + config.getAppKey() + config.getAppSecret());
        paramMap.put(SdkConstant.PARAM_SIGNATURE, signature);
        return paramMap;
    }

    /**
     * @return
     */
    private static Map<String, Object> buildMapWithoutSign(AppConfig config) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(SdkConstant.PARAM_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        paramMap.put(SdkConstant.PARAM_APP_KEY, config.getAppKey());
        return paramMap;
    }

}
