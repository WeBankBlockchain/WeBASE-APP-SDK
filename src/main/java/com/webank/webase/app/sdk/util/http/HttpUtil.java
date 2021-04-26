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
import com.webank.webase.app.sdk.exception.ApiException;
import com.webank.webase.app.sdk.util.AesUtils;
import com.webank.webase.app.sdk.util.JacksonUtil;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A util to send common http request.
 */
@Slf4j
public class HttpUtil {

    public static final String EMPTY_RESPONSE = "";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 连接超时时间, 默认 30s.
     */
    public static final int DEFAULT_CONNECT_TIMEOUT = 30;
    /**
     * 默认读取超时时间, 默认 30s.
     */
    public static final int DEFAULT_READ_TIMEOUT = 30;
    /**
     * 默认写数据超时时间, 默认 30s.
     */
    public static final int DEFAULT_WRITE_TIMEOUT = 30;

    // avoid creating several instances, should be singleon
    private static OkHttpClient client = null;

    /**
     * Init http client with timeout parameters.
     *
     * @param connectTimeout
     * @param readTimeout
     * @param writeTimeout
     */
    public static void init(int connectTimeout, int readTimeout, int writeTimeout) {
        log.info(
                "Init http util with configurations: connectTimeout:[{}], readTimeout:[{}], writeTimeout:[{}]",
                connectTimeout, readTimeout, writeTimeout);

        client = new OkHttpClient.Builder().retryOnConnectionFailure(true)
                .connectTimeout(connectTimeout == 0 ? DEFAULT_CONNECT_TIMEOUT : connectTimeout,
                        TimeUnit.SECONDS) // 连接超时
                .readTimeout(readTimeout == 0 ? DEFAULT_READ_TIMEOUT : readTimeout,
                        TimeUnit.SECONDS) // 读取超时
                .writeTimeout(writeTimeout == 0 ? DEFAULT_WRITE_TIMEOUT : writeTimeout,
                        TimeUnit.SECONDS) // 写超时
                .build();
    }

    /**
     *
     * @param url
     * @param queryMap
     * @param body
     * @return
     */
    public static String call(AppConfig config, String api, Map<String, Object> queryMap,
            Object body, int type) {
        // check if client is null
        if (client == null) {
            log.info("Http util was not initialized yet, init with default timeout configs....");
            init(DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT, DEFAULT_WRITE_TIMEOUT);
        }
        String url = String.format("%s%s", config.getNodeManagerUrl(), api);
        String appSecret = config.getAppSecret();
        boolean isTransferEncrypt = config.isTransferEncrypt();
        
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.addHeader(SdkConstant.PARAM_APP_KEY, config.getAppKey());
        requestBuilder.addHeader(SdkConstant.PARAM_IS_TRANSFER_ENCRYPT,
                String.valueOf(isTransferEncrypt));

        log.info("Start Http request:[{}:{}:{}]", url, JacksonUtil.toJSONString(queryMap),
                JacksonUtil.toJSONString(body));

        if (type == HttpTypeEnum.NORMAL_TYPE.getType()) {
            HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
            for (String key : queryMap.keySet()) {
                Object value = queryMap.get(key);
                if (value != null) {
                    builder.addQueryParameter(key, String.valueOf(value));
                }
            } ;

            url = builder.build().toString();
            if (body != null) {
                String bodyStr = JacksonUtil.toJSONString(body);
                if (isTransferEncrypt) {
                    bodyStr = AesUtils.encrypt(JacksonUtil.toJSONString(body), appSecret.substring(0, 16));
                }
                log.debug("Http request bodyStr:[{}]", bodyStr);
                requestBuilder.post(RequestBody.create(bodyStr, JSON));
            }
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MediaType.parse("multipart/form-data"));

            for (String key : queryMap.keySet()) {
                Object value = queryMap.get(key);
                if (value instanceof File) {
                    File file = (File) value;
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(file, null));
                } else {
                    if (value != null) {
                        builder.addFormDataPart(key, String.valueOf(value));
                    }
                }
            }
            requestBuilder.post(builder.build());
        }

        String responseString = EMPTY_RESPONSE;
        try {
            Request request = requestBuilder.url(url).build();
            Response response = client.newCall(request).execute();
            responseString = response.body().string();
            log.debug("Http response baseStr:[{}]", responseString);
            if (response.code() == 200 && isTransferEncrypt) {
                responseString = AesUtils.decrypt(responseString.replace("\"", ""),
                        appSecret.substring(0, 16));
            }
        } catch (Exception e) {
            log.error("Http request failed: [{}:{}:{}]", url, JacksonUtil.toJSONString(body), e);
            throw new ApiException(ApiErrorEnum.HTTP_REQUEST_ERROR);
        } finally {
            log.info("Request:[{}:{}]; Response:[{}] ", url, JacksonUtil.toJSONString(body),
                    responseString);
        }
        return responseString;
    }

    public static String post(AppConfig config, String api, Map<String, Object> queryMap,
            Object body) {
        return post(config, api, queryMap, body, HttpTypeEnum.NORMAL_TYPE.getType());
    }

    public static String post(AppConfig config, String api, Map<String, Object> queryMap,
            Object body, int type) {
        return call(config, api, queryMap, body, type);
    }

    public static String get(AppConfig config, String api, Map<String, Object> queryMap) {
        return call(config, api, queryMap, null, HttpTypeEnum.NORMAL_TYPE.getType());
    }
}
