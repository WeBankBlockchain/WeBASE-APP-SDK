/**
 * Copyright 2014-2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.app.sdk.client;

import static com.webank.webase.app.sdk.constant.SdkConstant.Api.ACCOUNT_ADD;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.ACCOUNT_LIST;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.APP_REGISTER;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.BASIC_INFO;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.CONTRACT_ADDRESS_SAVE;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.CONTRACT_SOURCE_SAVE;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.DB_INFO;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.FRONT_NODE_LIST;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.GROUP_LIST;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.IMPORT_P12;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.IMPORT_PEM;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.IMPORT_PRIVATEKEY;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.IMPORT_PUBLICKEY;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.NEW_USER;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.NODE_INFO;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.NODE_LIST;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.PASSWORD_UPDATE;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.ROLE_LIST;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.SDK_CERT;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.USER_INFO;
import static com.webank.webase.app.sdk.constant.SdkConstant.Api.USER_LIST;
import com.webank.webase.app.sdk.config.AppConfig;
import com.webank.webase.app.sdk.constant.ApiErrorEnum;
import com.webank.webase.app.sdk.dto.req.ReqAccountAdd;
import com.webank.webase.app.sdk.dto.req.ReqAppRegister;
import com.webank.webase.app.sdk.dto.req.ReqContractAddressSave;
import com.webank.webase.app.sdk.dto.req.ReqContractSourceSave;
import com.webank.webase.app.sdk.dto.req.ReqGetAccountList;
import com.webank.webase.app.sdk.dto.req.ReqGetNodeInfo;
import com.webank.webase.app.sdk.dto.req.ReqGetNodeList;
import com.webank.webase.app.sdk.dto.req.ReqGetUserList;
import com.webank.webase.app.sdk.dto.req.ReqImportP12;
import com.webank.webase.app.sdk.dto.req.ReqImportPem;
import com.webank.webase.app.sdk.dto.req.ReqImportPrivateKey;
import com.webank.webase.app.sdk.dto.req.ReqImportPublicKey;
import com.webank.webase.app.sdk.dto.req.ReqNewUser;
import com.webank.webase.app.sdk.dto.req.ReqPasswordUpdate;
import com.webank.webase.app.sdk.dto.rsp.RspAccountInfo;
import com.webank.webase.app.sdk.dto.rsp.RspBasicInfo;
import com.webank.webase.app.sdk.dto.rsp.RspDbInfo;
import com.webank.webase.app.sdk.dto.rsp.RspFrontInfo;
import com.webank.webase.app.sdk.dto.rsp.RspGroupInfo;
import com.webank.webase.app.sdk.dto.rsp.RspNodeInfo;
import com.webank.webase.app.sdk.dto.rsp.RspRoleInfo;
import com.webank.webase.app.sdk.dto.rsp.RspSdkCertInfo;
import com.webank.webase.app.sdk.dto.rsp.RspUserInfo;
import com.webank.webase.app.sdk.dto.rsp.base.Response;
import com.webank.webase.app.sdk.exception.ApiException;
import com.webank.webase.app.sdk.util.JacksonUtil;
import com.webank.webase.app.sdk.util.http.Http;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

@Slf4j
public class AppService {

    /**
     * check app config from node manager.
     * 
     * @param config
     */
    public static void checkAppConfig(AppConfig config) {
        try {
            String response = Http.get(config, BASIC_INFO);
            Response.checkResponse(response);
        } catch (ApiException ae) {
            throw ae;
        } catch (Exception e) {
            log.error("App config:[{}:{}:{}] init error", config.getNodeManagerUrl(),
                    config.getAppKey(), config.getAppSecret(), e);
            throw new ApiException(ApiErrorEnum.CONFIG_INIT_ERROR);
        }
    }

    /**
     * appRegister.
     * 
     * @param appConfig
     * @param reqAppRegister
     */
    public static void appRegister(AppConfig appConfig, ReqAppRegister reqAppRegister) {
        String response = Http.post(appConfig, APP_REGISTER, reqAppRegister);
        Response.checkResponse(response);
    }

    /**
     * accountList.
     * 
     * @param appConfig
     * @param reqGetAccountList
     * @return
     */
    public static Pair<Long, List<RspAccountInfo>> accountList(AppConfig appConfig,
            ReqGetAccountList reqGetAccountList) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap = JacksonUtil.toMap(reqGetAccountList);
        String response = Http.get(appConfig, ACCOUNT_LIST, paramMap);
        return Response.toListAndCount(response, RspAccountInfo.class);
    }

    /**
     * roleList.
     * 
     * @param appConfig
     * @return
     */
    public static List<RspRoleInfo> roleList(AppConfig appConfig) {
        String response = Http.get(appConfig, ROLE_LIST);
        return Response.toList(response, RspRoleInfo.class);
    }

    /**
     * accountAdd.
     * 
     * @param appConfig
     * @param reqAccountAdd
     */
    public static RspAccountInfo accountAdd(AppConfig appConfig, ReqAccountAdd reqAccountAdd) {
        String response = Http.post(appConfig, ACCOUNT_ADD, reqAccountAdd);
        return Response.toObject(response, RspAccountInfo.class);
    }

    /**
     * passwordUpdate.
     * 
     * @param appConfig
     * @param reqPasswordUpdate
     */
    public static void passwordUpdate(AppConfig appConfig, ReqPasswordUpdate reqPasswordUpdate) {
        String response = Http.post(appConfig, PASSWORD_UPDATE, reqPasswordUpdate);
        Response.checkResponse(response);
    }

    /**
     * basicInfo.
     * 
     * @param appConfig
     * @return
     */
    public static RspBasicInfo basicInfo(AppConfig appConfig) {
        String response = Http.get(appConfig, BASIC_INFO);
        return Response.toObject(response, RspBasicInfo.class);
    }

    /**
     * groupList.
     * 
     * @param appConfig
     * @param groupStatus
     * @return
     */
    public static List<RspGroupInfo> groupList(AppConfig appConfig, Integer groupStatus) {
        Map<String, Object> paramMap = new HashMap<>();
        if (groupStatus != null) {
            paramMap.put("groupStatus", groupStatus);
        }
        String response = Http.get(appConfig, GROUP_LIST, paramMap);
        return Response.toList(response, RspGroupInfo.class);
    }

    /**
     * nodeList.
     * 
     * @param appConfig
     * @param reqGetNodeList
     * @return
     */
    public static Pair<Long, List<RspNodeInfo>> nodeList(AppConfig appConfig,
            ReqGetNodeList reqGetNodeList) {
        String response = Http.get(appConfig, NODE_LIST, JacksonUtil.toMap(reqGetNodeList));
        return Response.toListAndCount(response, RspNodeInfo.class);
    }

    /**
     * nodeInfo.
     * 
     * @param appConfig
     * @param reqGetNodeInfo
     * @return
     */
    public static RspNodeInfo nodeInfo(AppConfig appConfig, ReqGetNodeInfo reqGetNodeInfo) {
        String response = Http.get(appConfig, NODE_INFO, JacksonUtil.toMap(reqGetNodeInfo));
        return Response.toObject(response, RspNodeInfo.class);
    }

    /**
     * frontNodeList.
     * 
     * @param appConfig
     * @param reqGetNodeInfo
     * @return
     */
    public static List<RspFrontInfo> frontNodeList(AppConfig appConfig,
            ReqGetNodeInfo reqGetNodeInfo) {
        String response = Http.get(appConfig, FRONT_NODE_LIST, JacksonUtil.toMap(reqGetNodeInfo));
        return Response.toList(response, RspFrontInfo.class);
    }


    /**
     * sdkCert.
     * 
     * @param appConfig
     * @return
     */
    public static List<RspSdkCertInfo> sdkCert(AppConfig appConfig) {
        String response = Http.get(appConfig, SDK_CERT);
        return Response.toListWithoutCount(response, RspSdkCertInfo.class);
    }

    /**
     * newUser.
     * 
     * @param appConfig
     * @param reqNewUser
     */
    public static RspUserInfo newUser(AppConfig appConfig, ReqNewUser reqNewUser) {
        String response = Http.post(appConfig, NEW_USER, reqNewUser);
        return Response.toObject(response, RspUserInfo.class);
    }

    /**
     * userList.
     * 
     * @param appConfig
     * @param reqGetUserList
     * @return
     */
    public static Pair<Long, List<RspUserInfo>> userList(AppConfig appConfig,
            ReqGetUserList reqGetUserList) {
        String response = Http.get(appConfig, USER_LIST, JacksonUtil.toMap(reqGetUserList));
        return Response.toListAndCount(response, RspUserInfo.class);
    }

    /**
     * userInfo.
     * 
     * @param appConfig
     * @param userId
     * @return
     */
    public static RspUserInfo userInfo(AppConfig appConfig, Integer userId) {
        Map<String, Object> paramMap = new HashMap<>();
        if (userId != null) {
            paramMap.put("userId", userId);
        }
        String response = Http.get(appConfig, USER_INFO, paramMap);
        return Response.toObject(response, RspUserInfo.class);
    }

    /**
     * importPrivateKey.
     * 
     * @param appConfig
     * @param reqImportPublicKey
     */
    public static RspUserInfo importPublicKey(AppConfig appConfig,
            ReqImportPublicKey reqImportPublicKey) {
        String response = Http.post(appConfig, IMPORT_PUBLICKEY, reqImportPublicKey);
        return Response.toObject(response, RspUserInfo.class);
    }

    /**
     * importPrivateKey.
     * 
     * @param appConfig
     * @param reqImportPrivateKey
     */
    public static RspUserInfo importPrivateKey(AppConfig appConfig,
            ReqImportPrivateKey reqImportPrivateKey) {
        String response = Http.post(appConfig, IMPORT_PRIVATEKEY, reqImportPrivateKey);
        return Response.toObject(response, RspUserInfo.class);
    }

    /**
     * importPemPrivateKey.
     * 
     * @param appConfig
     * @param reqImportPem
     */
    public static RspUserInfo importPemPrivateKey(AppConfig appConfig, ReqImportPem reqImportPem) {
        String response = Http.post(appConfig, IMPORT_PEM, reqImportPem);
        return Response.toObject(response, RspUserInfo.class);
    }

    /**
     * importP12PrivateKey.
     * 
     * @param appConfig
     * @param reqImportPem
     */
    public static RspUserInfo importP12PrivateKey(AppConfig appConfig, ReqImportP12 reqImportP12) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap = JacksonUtil.toMap(reqImportP12);
        paramMap.put("p12File", new File(reqImportP12.getP12File()));
        String response = Http.postFile(appConfig, IMPORT_P12, paramMap);
        return Response.toObject(response, RspUserInfo.class);
    }

    /**
     * contractSourceSave.
     * 
     * @param appConfig
     * @param reqContractAddressSave
     */
    public static void contractSourceSave(AppConfig appConfig,
            ReqContractSourceSave reqContractSourceSave) {
        String response = Http.post(appConfig, CONTRACT_SOURCE_SAVE, reqContractSourceSave);
        Response.checkResponse(response);
    }

    /**
     * contractAddressSave.
     * 
     * @param appConfig
     * @param reqContractSourceSave
     */
    public static void contractAddressSave(AppConfig appConfig,
            ReqContractAddressSave reqContractAddressSave) {
        String response = Http.post(appConfig, CONTRACT_ADDRESS_SAVE, reqContractAddressSave);
        Response.checkResponse(response);
    }

    /**
     * dbInfo.
     * 
     * @param appConfig
     * @return
     */
    public static RspDbInfo dbInfo(AppConfig appConfig) {
        String response = Http.get(appConfig, DB_INFO);
        return Response.toObject(response, RspDbInfo.class);
    }

}
