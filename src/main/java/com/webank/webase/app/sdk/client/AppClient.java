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

import com.webank.webase.app.sdk.config.AppConfig;
import com.webank.webase.app.sdk.config.HttpConfig;
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
import com.webank.webase.app.sdk.util.http.HttpUtil;
import java.util.List;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

/**
 * APP client, new AppClient before request.
 */
@Data
public class AppClient {

    /**
     * ????????????.
     */
    protected AppConfig appConfig;

    /**
     * ??????????????????.
     *
     * @param nodeManagerUrl
     * @param appKey
     * @param appSecret
     */
    public AppClient(String nodeManagerUrl, String appKey, String appSecret,
            boolean isTransferEncrypt, HttpConfig httpConfig) {
        appConfig = AppConfig.newAppConfig(nodeManagerUrl, appKey, appSecret, isTransferEncrypt);
        // init http
        if (httpConfig != null) {
            HttpUtil.init(httpConfig.getConnectTimeout(), httpConfig.getReadTimeout(),
                    httpConfig.getWriteTimeout());
        }
        // check app config from server
        AppService.checkAppConfig(appConfig);
    }

    /**
     * ??????????????????.
     * 
     * @param reqAppRegister
     */
    public void appRegister(ReqAppRegister reqAppRegister) {
        AppService.appRegister(appConfig, reqAppRegister);
    }

    /**
     * ??????????????????.
     * 
     * @param reqGetAccountList
     * @return
     */
    public Pair<Long, List<RspAccountInfo>> accountList(ReqGetAccountList reqGetAccountList) {
        return AppService.accountList(appConfig, reqGetAccountList);
    }

    /**
     * ??????????????????.
     * 
     * @return
     */
    public List<RspRoleInfo> roleList() {
        return AppService.roleList(appConfig);
    }

    /**
     * ????????????.
     * 
     * @param reqAccountAdd
     * @return
     */
    public RspAccountInfo accountAdd(ReqAccountAdd reqAccountAdd) {
        return AppService.accountAdd(appConfig, reqAccountAdd);
    }

    /**
     * ????????????.
     * 
     * @param reqPasswordUpdate
     */
    public void passwordUpdate(ReqPasswordUpdate reqPasswordUpdate) {
        AppService.passwordUpdate(appConfig, reqPasswordUpdate);
    }

    /**
     * ?????????????????????.
     * 
     * @return
     */
    public RspBasicInfo basicInfo() {
        return AppService.basicInfo(appConfig);
    }

    /**
     * ??????????????????.
     * 
     * @param groupStatus
     * @return
     */
    public List<RspGroupInfo> groupList(Integer groupStatus) {
        return AppService.groupList(appConfig, groupStatus);
    }

    /**
     * ????????????????????????.
     * 
     * @param reqGetNodeList
     * @return
     */
    public Pair<Long, List<RspNodeInfo>> nodeList(ReqGetNodeList reqGetNodeList) {
        return AppService.nodeList(appConfig, reqGetNodeList);
    }

    /**
     * ??????????????????.
     * 
     * @param reqGetNodeInfo
     * @return
     */
    public RspNodeInfo nodeInfo(ReqGetNodeInfo reqGetNodeInfo) {
        return AppService.nodeInfo(appConfig, reqGetNodeInfo);
    }

    /**
     * ????????????????????????.
     * 
     * @param reqGetNodeInfo
     * @return
     */
    public List<RspFrontInfo> frontNodeList(ReqGetNodeInfo reqGetNodeInfo) {
        return AppService.frontNodeList(appConfig, reqGetNodeInfo);
    }


    /**
     * ??????sdk????????????.
     * 
     * @return
     */
    public List<RspSdkCertInfo> sdkCert() {
        return AppService.sdkCert(appConfig);
    }

    /**
     * ??????????????????.
     * 
     * @param reqNewUser
     */
    public RspUserInfo newUser(ReqNewUser reqNewUser) {
        return AppService.newUser(appConfig, reqNewUser);
    }

    /**
     * ??????????????????.
     * 
     * @param reqGetUserList
     * @return
     */
    public Pair<Long, List<RspUserInfo>> userList(ReqGetUserList reqGetUserList) {
        return AppService.userList(appConfig, reqGetUserList);
    }

    /**
     * ??????????????????.
     * 
     * @param userId
     * @return
     */
    public RspUserInfo userInfo(Integer userId) {
        return AppService.userInfo(appConfig, userId);
    }

    /**
     * ??????????????????.
     * 
     * @param reqImportPublicKey
     * @return
     */
    public RspUserInfo importPublicKey(ReqImportPublicKey reqImportPublicKey) {
        return AppService.importPublicKey(appConfig, reqImportPublicKey);
    }

    /**
     * ??????????????????.
     * 
     * @param reqImportPrivateKey
     * @return
     */
    public RspUserInfo importPrivateKey(ReqImportPrivateKey reqImportPrivateKey) {
        return AppService.importPrivateKey(appConfig, reqImportPrivateKey);
    }

    /**
     * ??????.pem??????.
     * 
     * @param reqImportPem
     * @return
     */
    public RspUserInfo importPemPrivateKey(ReqImportPem reqImportPem) {
        return AppService.importPemPrivateKey(appConfig, reqImportPem);
    }

    /**
     * ??????.p12??????.
     * 
     * @param reqImportPem
     * @return
     */
    public RspUserInfo importP12PrivateKey(ReqImportP12 reqImportP12) {
        return AppService.importP12PrivateKey(appConfig, reqImportP12);
    }

    /**
     * ??????????????????.
     * 
     * @param reqContractAddressSave
     * @return
     */
    public void contractSourceSave(ReqContractSourceSave reqContractSourceSave) {
        AppService.contractSourceSave(appConfig, reqContractSourceSave);
    }

    /**
     * ??????????????????.
     * 
     * @param reqContractSourceSave
     * @return
     */
    public void contractAddressSave(ReqContractAddressSave reqContractAddressSave) {
        AppService.contractAddressSave(appConfig, reqContractAddressSave);
    }

    /**
     * ?????????????????????.
     * 
     * @return
     */
    public RspDbInfo dbInfo() {
        return AppService.dbInfo(appConfig);
    }
}
