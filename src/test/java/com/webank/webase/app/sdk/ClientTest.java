package com.webank.webase.app.sdk;

import com.webank.webase.app.sdk.client.AppClient;
import com.webank.webase.app.sdk.config.HttpConfig;
import com.webank.webase.app.sdk.dto.req.ReqAppRegister;
import com.webank.webase.app.sdk.dto.req.ReqContractAddressSave;
import com.webank.webase.app.sdk.dto.req.ReqContractSourceSave;
import com.webank.webase.app.sdk.dto.req.ReqContractSourceSave.ContractSource;
import com.webank.webase.app.sdk.dto.req.ReqGetAccountList;
import com.webank.webase.app.sdk.dto.req.ReqGetNodeInfo;
import com.webank.webase.app.sdk.dto.req.ReqGetNodeList;
import com.webank.webase.app.sdk.dto.req.ReqGetUserList;
import com.webank.webase.app.sdk.dto.req.ReqImportP12;
import com.webank.webase.app.sdk.dto.req.ReqImportPem;
import com.webank.webase.app.sdk.dto.req.ReqImportPrivateKey;
import com.webank.webase.app.sdk.dto.req.ReqImportPublicKey;
import com.webank.webase.app.sdk.dto.req.ReqNewUser;
import com.webank.webase.app.sdk.dto.rsp.RspAccountInfo;
import com.webank.webase.app.sdk.dto.rsp.RspBasicInfo;
import com.webank.webase.app.sdk.dto.rsp.RspDbInfo;
import com.webank.webase.app.sdk.dto.rsp.RspFrontInfo;
import com.webank.webase.app.sdk.dto.rsp.RspGroupInfo;
import com.webank.webase.app.sdk.dto.rsp.RspNodeInfo;
import com.webank.webase.app.sdk.dto.rsp.RspSdkCertInfo;
import com.webank.webase.app.sdk.dto.rsp.RspUserInfo;
import com.webank.webase.app.sdk.util.JacksonUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ClientTest.
 */
public class ClientTest {

    // WeBASE-Node-Manager的url
    private static String url = "http://localhost:5001";
    private static String appKey = "RUPCNAsd";
    private static String appSecret = "65KiXNxUpPywVwQxM7SFsMHsKmCbpGrQ";

    public static String account = "admin";
    public static String userName = "alice";
    public static String contractVersion = "1.0.0";
    public static String contractName = "HelloWorld";

    private static AppClient appClient = null;

    public static void main(String[] args) {
        try {
            initClient();
            appRegister();
            accountList();
            basicInfo();
            groupList();
            nodeList();
            nodeInfo();
            frontNodeList();
            sdkCert();
            newUser();
            userList();
            userInfo();
            importPublicKey();
            importPrivateKey();
            importPemPrivateKey();
            importP12PrivateKey();
            contractSourceSave();
            contractAddressSave();
            dbInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void initClient() {
        // 未设置httpConfig时，默认http连接均为30s
        HttpConfig httpConfig = new HttpConfig(30, 30, 30);
        appClient = new AppClient(url, appKey, appSecret, true, httpConfig);
        System.out.println("testInitClient:" + JacksonUtil.objToString(appClient));
    }

    public static void appRegister() throws Exception {
        try {
            ReqAppRegister req = new ReqAppRegister();
            req.setAppIp("127.0.0.1");
            req.setAppPort(5001);
            req.setAppLink("https://127.0.0.1:5001/");
            appClient.appRegister(req);
            System.out.println("appRegister end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void accountList() {
        ReqGetAccountList reqGetAccountList = new ReqGetAccountList();
        reqGetAccountList.setPageNumber(1);
        reqGetAccountList.setPageSize(2);
        // reqGetAccountList.setAccount("admin");
        Pair<Long, List<RspAccountInfo>> resp = appClient.accountList(reqGetAccountList);
        System.out.println("accountList count:" + resp.getKey() + " list:"
                + JacksonUtil.objToString(resp.getValue()));
    }

    public static void basicInfo() {
        RspBasicInfo resp = appClient.basicInfo();
        System.out.println("basicInfo:" + JacksonUtil.objToString(resp));
    }

    public static void groupList() {
        List<RspGroupInfo> resp = appClient.groupList(null);
        System.out.println("groupList list:" + JacksonUtil.objToString(resp));
    }

    public static void nodeList() {
        ReqGetNodeList reqGetNodeList = new ReqGetNodeList();
        reqGetNodeList.setPageNumber(1);
        reqGetNodeList.setPageSize(2);
        Pair<Long, List<RspNodeInfo>> resp = appClient.nodeList(reqGetNodeList);
        System.out.println("nodeList count:" + resp.getKey() + " list:"
                + JacksonUtil.objToString(resp.getValue()));
    }

    public static void nodeInfo() {
        ReqGetNodeInfo reqGetNodeInfo = new ReqGetNodeInfo();
        reqGetNodeInfo.setGroupId(1);
        reqGetNodeInfo.setNodeId("");
        RspNodeInfo resp = appClient.nodeInfo(reqGetNodeInfo);
        System.out.println("nodeInfo:" + JacksonUtil.objToString(resp));
    }

    public static void frontNodeList() {
        ReqGetNodeInfo reqGetNodeInfo = new ReqGetNodeInfo();
        reqGetNodeInfo.setGroupId(1);
        List<RspFrontInfo> resp = appClient.frontNodeList(reqGetNodeInfo);
        System.out.println("frontNodeList list:" + JacksonUtil.objToString(resp));
    }

    public static void sdkCert() {
        List<RspSdkCertInfo> resp = appClient.sdkCert();
        System.out.println("sdkCert:" + JacksonUtil.objToString(resp));
    }

    public static void newUser() {
        try {
            ReqNewUser reqNewUser = new ReqNewUser();
            reqNewUser.setGroupId(1);
            reqNewUser.setUserName(userName);
            reqNewUser.setAccount(account);
            reqNewUser.setDescription("test");
            RspUserInfo resp = appClient.newUser(reqNewUser);
            System.out.println("newUser:" + JacksonUtil.objToString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void userList() {
        ReqGetUserList reqGetUserList = new ReqGetUserList();
        reqGetUserList.setGroupId(1);
        reqGetUserList.setAccount(account);
        Pair<Long, List<RspUserInfo>> resp = appClient.userList(reqGetUserList);
        System.out.println("userList count:" + resp.getKey() + " list:"
                + JacksonUtil.objToString(resp.getValue()));
    }

    public static void userInfo() {
        RspUserInfo resp = appClient.userInfo(1);
        System.out.println("userInfo:" + JacksonUtil.objToString(resp));
    }

    public static void importPublicKey() {
        try {
            ReqImportPublicKey reqImportPublicKey = new ReqImportPublicKey();
            reqImportPublicKey.setGroupId(1);
            reqImportPublicKey.setUserName("u2");
            reqImportPublicKey.setAccount(account);
            reqImportPublicKey.setPublicKey(
                    "0x98c4e9896dfa062c7555ede0f1509bda90668902ee9a3b382a3941869d3d69026ece966e1afe9f9de41c2e762750dee8d7df47439b3340a22cd620e2f6975ef8");
            reqImportPublicKey.setDescription("test");
            RspUserInfo resp = appClient.importPublicKey(reqImportPublicKey);
            System.out.println("importPublicKey:" + JacksonUtil.objToString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importPrivateKey() {
        try {
            ReqImportPrivateKey reqImportPrivateKey = new ReqImportPrivateKey();
            reqImportPrivateKey.setGroupId(1);
            reqImportPrivateKey.setUserName("u3");
            reqImportPrivateKey.setAccount(account);
            reqImportPrivateKey.setPrivateKey(
                    "Yjk4YzM3Y2EzNTMxMzNiOWI2MWUwOTMxODhmOTk2NTc2MGYxMTBhMTljNTI2MmY3NTczMDVkNThlOGM3ZWNlMA==");
            reqImportPrivateKey.setDescription("test");
            RspUserInfo resp = appClient.importPrivateKey(reqImportPrivateKey);
            System.out.println("importPrivateKey:" + JacksonUtil.objToString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importPemPrivateKey() {
        try {
            ReqImportPem reqImportPem = new ReqImportPem();
            reqImportPem.setGroupId(1);
            reqImportPem.setUserName("u4");
            reqImportPem.setAccount(account);
            reqImportPem.setPemContent(
                    "-----BEGIN PRIVATE KEY-----\nMIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgC8TbvFSMA9y3CghFt51/\nXmExewlioX99veYHOV7dTvOhRANCAASZtMhCTcaedNP+H7iljbTIqXOFM6qm5aVs\nfM/yuDBK2MRfFbfnOYVTNKyOSnmkY+xBfCR8Q86wcsQm9NZpkmFK\n-----END PRIVATE KEY-----\n");
            reqImportPem.setDescription("test");
            RspUserInfo resp = appClient.importPemPrivateKey(reqImportPem);
            System.out.println("importPemPrivateKey:" + JacksonUtil.objToString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importP12PrivateKey() {
        try {
            ReqImportP12 reqImportP12 = new ReqImportP12();
            reqImportP12.setGroupId(1);
            reqImportP12.setUserName("u5");
            reqImportP12.setAccount(account);
            reqImportP12.setP12File("D:\\test_key_0x994dfdaed38462af6915615b6e2959db1ef3b8ad.p12");
            reqImportP12.setDescription("test");
            RspUserInfo resp = appClient.importP12PrivateKey(reqImportP12);
            System.out.println("importP12PrivateKey:" + JacksonUtil.objToString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void contractSourceSave() {
        try {
            ReqContractSourceSave reqContractSourceSave = new ReqContractSourceSave();
            reqContractSourceSave.setAccount(account);
            reqContractSourceSave.setContractVersion(contractVersion);

            List<ContractSource> contractList = new ArrayList<>();
            ContractSource contractSource = new ContractSource();
            contractSource.setContractName(contractName);
            contractSource.setContractSource(
                    "cHJhZ21hIHNvbGlkaXR5IF4wLjQuMjsNCmNvbnRyYWN0IEhlbGxvV29ybGR7DQogICAgc3RyaW5nIG5hbWU7DQogICAgZXZlbnQgU2V0TmFtZShzdHJpbmcgbmFtZSk7DQogICAgZnVuY3Rpb24gZ2V0KCljb25zdGFudCByZXR1cm5zKHN0cmluZyl7DQogICAgICAgIHJldHVybiBuYW1lOw0KICAgIH0NCiAgICBmdW5jdGlvbiBzZXQoc3RyaW5nIG4pew0KICAgICAgICBlbWl0IFNldE5hbWUobik7DQogICAgICAgIG5hbWU9bjsNCiAgICB9DQp9");
            contractSource.setContractAbi(
                    "[{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"n\",\"type\":\"string\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"name\",\"type\":\"string\"}],\"name\":\"SetName\",\"type\":\"event\"}]");
            contractSource.setBytecodeBin(
                    "608060405234801561001057600080fd5b50610373806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063299f7f9d146100515780633590b49f146100e1575b600080fd5b34801561005d57600080fd5b5061006661014a565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100a657808201518184015260208101905061008b565b50505050905090810190601f1680156100d35780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100ed57600080fd5b50610148600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506101ec565b005b606060008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101e25780601f106101b7576101008083540402835291602001916101e2565b820191906000526020600020905b8154815290600101906020018083116101c557829003601f168201915b5050505050905090565b7f05432a43e07f36a8b98100b9cb3631e02f8e796b0a06813610ce8942e972fb81816040518080602001828103825283818151815260200191508051906020019080838360005b8381101561024e578082015181840152602081019050610233565b50505050905090810190601f16801561027b5780820380516001836020036101000a031916815260200191505b509250505060405180910390a1806000908051906020019061029e9291906102a2565b5050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e357805160ff1916838001178555610311565b82800160010185558215610311579182015b828111156103105782518255916020019190600101906102f5565b5b50905061031e9190610322565b5090565b61034491905b80821115610340576000816000905550600101610328565b5090565b905600a165627a7a7230582077e2db38eecb729562ac614505b3507007a6acce30fd30971bd0501a019eb02d0029");
            contractList.add(contractSource);
            reqContractSourceSave.setContractList(contractList);

            appClient.contractSourceSave(reqContractSourceSave);
            System.out.println("contractSourceSave end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void contractAddressSave() {
        try {
            ReqContractAddressSave reqContractAddressSave = new ReqContractAddressSave();
            reqContractAddressSave.setGroupId(1);
            reqContractAddressSave.setContractName(contractName);
            reqContractAddressSave.setContractPath("test");
            reqContractAddressSave.setContractVersion(contractVersion);
            reqContractAddressSave.setContractAddress("0x072d9db9e8fa914b7638feb8c3d49ef8fd8258c9");
            appClient.contractAddressSave(reqContractAddressSave);
            System.out.println("contractAddressSave end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dbInfo() {
        RspDbInfo resp = appClient.dbInfo();
        System.out.println("dbInfo:" + JacksonUtil.objToString(resp));
    }
}
