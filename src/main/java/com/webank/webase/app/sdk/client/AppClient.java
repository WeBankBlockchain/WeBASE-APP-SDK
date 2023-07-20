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
     * 配置信息.
     */
    protected AppConfig appConfig;

    /**
     * 初始化客户端.
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
     * 应用注册接口.
     * 
     * @param reqAppRegister
     */
    public void appRegister(ReqAppRegister reqAppRegister) {
        AppService.appRegister(appConfig, reqAppRegister);
    }

    /**
     * 查询帐号列表.
     * 
     * @param reqGetAccountList
     * @return
     */
    public Pair<Long, List<RspAccountInfo>> accountList(ReqGetAccountList reqGetAccountList) {
        return AppService.accountList(appConfig, reqGetAccountList);
    }

    /**
     * 查询角色列表.
     * 
     * @return
     */
    public List<RspRoleInfo> roleList() {
        return AppService.roleList(appConfig);
    }

    /**
     * 新增帐号.
     * 
     * @param reqAccountAdd
     * @return
     */
    public RspAccountInfo accountAdd(ReqAccountAdd reqAccountAdd) {
        return AppService.accountAdd(appConfig, reqAccountAdd);
    }

    /**
     * 更新密码.
     * 
     * @param reqPasswordUpdate
     */
    public void passwordUpdate(ReqPasswordUpdate reqPasswordUpdate) {
        AppService.passwordUpdate(appConfig, reqPasswordUpdate);
    }

    /**
     * 查询链基本信息.
     * 
     * @return
     */
    public RspBasicInfo basicInfo(String groupId) {
        return AppService.basicInfo(appConfig, groupId);
    }

    /**
     * 查询链基本信息.
     *
     * @return
     */
    public Integer encryptType(String groupId) {
        return AppService.encryptType(appConfig, groupId);
    }

    /**
     * 查询群组列表.
     * 
     * @param groupStatus
     * @return
     */
    public List<RspGroupInfo> groupList(Integer groupStatus) {
        return AppService.groupList(appConfig, groupStatus);
    }

    /**
     * 查询所有节点列表.
     * 
     * @param reqGetNodeList
     * @return
     */
    public Pair<Long, List<RspNodeInfo>> nodeList(ReqGetNodeList reqGetNodeList) {
        return AppService.nodeList(appConfig, reqGetNodeList);
    }

    /**
     * 查询节点信息.
     * 
     * @param reqGetNodeInfo
     * @return
     */
    public RspNodeInfo nodeInfo(ReqGetNodeInfo reqGetNodeInfo) {
        return AppService.nodeInfo(appConfig, reqGetNodeInfo);
    }

    /**
     * 查询前置节点列表.
     * 
     * @param reqGetNodeInfo
     * @return
     */
    public List<RspFrontInfo> frontNodeList(ReqGetNodeInfo reqGetNodeInfo) {
        return AppService.frontNodeList(appConfig, reqGetNodeInfo);
    }


    /**
     * 查询sdk证书信息.
     * 
     * @return
     */
    public List<RspSdkCertInfo> sdkCert() {
        return AppService.sdkCert(appConfig);
    }

    /**
     * 新增私钥用户.
     * 
     * @param reqNewUser
     */
    public RspUserInfo newUser(ReqNewUser reqNewUser) {
        return AppService.newUser(appConfig, reqNewUser);
    }

    /**
     * 查询用户列表.
     * 
     * @param reqGetUserList
     * @return
     */
    public Pair<Long, List<RspUserInfo>> userList(ReqGetUserList reqGetUserList) {
        return AppService.userList(appConfig, reqGetUserList);
    }

    /**
     * 查询用户信息.
     * 
     * @param userId
     * @return
     */
    public RspUserInfo userInfo(Integer userId) {
        return AppService.userInfo(appConfig, userId);
    }

    /**
     * 导入公钥用户.
     * 
     * @param reqImportPublicKey
     * @return
     */
    public RspUserInfo importPublicKey(ReqImportPublicKey reqImportPublicKey) {
        return AppService.importPublicKey(appConfig, reqImportPublicKey);
    }

    /**
     * 导入私钥用户.
     * 
     * @param reqImportPrivateKey
     * @return
     */
    public RspUserInfo importPrivateKey(ReqImportPrivateKey reqImportPrivateKey) {
        return AppService.importPrivateKey(appConfig, reqImportPrivateKey);
    }

    /**
     * 导入.pem私钥.
     * 
     * @param reqImportPem
     * @return
     */
    public RspUserInfo importPemPrivateKey(ReqImportPem reqImportPem) {
        return AppService.importPemPrivateKey(appConfig, reqImportPem);
    }

    /**
     * 导入.p12私钥.
     * 
     * @param reqImportP12
     * @return
     */
    public RspUserInfo importP12PrivateKey(ReqImportP12 reqImportP12) {
        return AppService.importP12PrivateKey(appConfig, reqImportP12);
    }

    /**
     * 合约原文保存.
     * 
     * @param reqContractSourceSave
     * @return
     */
    public void contractSourceSave(ReqContractSourceSave reqContractSourceSave) {
        AppService.contractSourceSave(appConfig, reqContractSourceSave);
    }

    /**
     * 合约地址保存.
     * 
     * @param reqContractAddressSave
     * @return
     */
    public void contractAddressSave(ReqContractAddressSave reqContractAddressSave) {
        AppService.contractAddressSave(appConfig, reqContractAddressSave);
    }

    /**
     * 查询数据库信息.
     * 
     * @return
     */
    public RspDbInfo dbInfo() {
        return AppService.dbInfo(appConfig);
    }
}
