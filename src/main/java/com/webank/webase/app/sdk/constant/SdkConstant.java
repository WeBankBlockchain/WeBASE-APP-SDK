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

/**
 * SdkConstant.
 */
public class SdkConstant {

    // request params
    public static final String PARAM_APP_KEY = "appKey";
    public static final String PARAM_APP_SECRET = "appSecret";
    public static final String PARAM_TIMESTAMP = "timestamp";
    public static final String PARAM_SIGNATURE = "signature";
    public static final String PARAM_IS_TRANSFER_ENCRYPT = "isTransferEncrypt";

    public static final String[] ALL_PARAM_ARRAY =
            new String[]{PARAM_TIMESTAMP, PARAM_APP_KEY, PARAM_APP_SECRET, PARAM_SIGNATURE};

    public static final String CONFIG_URL = "node manager url";

    public static class Api {
        public static final String CHECK = "/WeBASE-Node-Manager/api/check";
        public static final String APP_REGISTER = "/WeBASE-Node-Manager/api/appRegister";
        public static final String ACCOUNT_LIST = "/WeBASE-Node-Manager/api/accountList";
        public static final String BASIC_INFO = "/WeBASE-Node-Manager/api/basicInfo";
        public static final String GROUP_LIST = "/WeBASE-Node-Manager/api/groupList";
        public static final String NODE_LIST = "/WeBASE-Node-Manager/api/nodeList";
        public static final String NODE_INFO = "/WeBASE-Node-Manager/api/nodeInfo";
        public static final String FRONT_NODE_LIST = "/WeBASE-Node-Manager/api/frontNodeList";
        public static final String SDK_CERT = "/WeBASE-Node-Manager/api/sdkCert";
        public static final String NEW_USER = "/WeBASE-Node-Manager/api/newUser";
        public static final String USER_LIST = "/WeBASE-Node-Manager/api/userList";
        public static final String USER_INFO = "/WeBASE-Node-Manager/api/userInfo";
        public static final String IMPORT_PUBLICKEY = "/WeBASE-Node-Manager/api/importPublicKey";
        public static final String IMPORT_PRIVATEKEY = "/WeBASE-Node-Manager/api/importPrivateKey";
        public static final String IMPORT_PEM = "/WeBASE-Node-Manager/api/importPem";
        public static final String IMPORT_P12 = "/WeBASE-Node-Manager/api/importP12";
        public static final String CONTRACT_SOURCE_SAVE = "/WeBASE-Node-Manager/api/contractSourceSave";
        public static final String CONTRACT_ADDRESS_SAVE = "/WeBASE-Node-Manager/api/contractAddressSave";
        public static final String DB_INFO = "/WeBASE-Node-Manager/api/dbInfo";
    }
}
