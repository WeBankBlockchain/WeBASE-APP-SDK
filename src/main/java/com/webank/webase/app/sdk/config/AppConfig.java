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
package com.webank.webase.app.sdk.config;

import com.webank.webase.app.sdk.constant.SdkConstant;
import com.webank.webase.app.sdk.util.CheckUtil;
import lombok.Data;

@Data
public class AppConfig {
    /**
     * 节点管理服务地址
     */
    private String nodeManagerUrl;
    /**
     * 应用Key
     */
    private String appKey;
    /**
     * 应用密码
     */
    private String appSecret;
    /**
     * 是否加密传输
     */
    private boolean isTransferEncrypt;


    /**
     * 不允许直接 new对象
     */
    private AppConfig() {}

    /**
     * 加载配置
     */
    public static AppConfig newAppConfig(String nodeManagerUrl, String appKey, String appSecret,
            boolean isTransferEncrypt) {

        CheckUtil.checkParamNotBlank(SdkConstant.CONFIG_URL, nodeManagerUrl);
        CheckUtil.checkParamNotBlank(SdkConstant.PARAM_APP_KEY, appKey);
        CheckUtil.checkParamNotBlank(SdkConstant.PARAM_APP_SECRET, appSecret);

        AppConfig newAppConfig = new AppConfig();
        newAppConfig.setNodeManagerUrl(nodeManagerUrl);
        newAppConfig.setAppKey(appKey);
        newAppConfig.setAppSecret(appSecret);
        newAppConfig.setTransferEncrypt(isTransferEncrypt);
        return newAppConfig;
    }
}
