## SDK使用说明

​	从`v1.5.1`开始，提供应用集成SDK，方便应用接入。接口API可以查看[WeBASE通用API](https://webasedoc.readthedocs.io/zh_CN/latest/docs/WeBASE-Node-Manager/appintegration.html#webaseapi)。调用之前需要添加依赖和初始化应用信息。

- `v1.5.1`及其以上版本，应用配置AppConfig的属性`isTransferEncrypt`需和WeBASE-Node-Manager的配置文件`/conf/application.yml`下的配置`constant.isTransferEncrypt`相同，默认为`true`。
- 如果`v1.5.0`需要使用SDK，应用配置AppConfig的属性`isTransferEncrypt`需设置为`false`。`v1.5.1`及其以上版本新增的接口调用不了。

### 添加依赖

- 添加 SDK 的依赖，以Gradle为例

```java
repositories {
    maven { url "http://maven.aliyun.com/nexus/content/groups/public/" }
    maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
}
dependencies {
    implementation 'com.webank:webase-app-sdk:1.5.1-SNAPSHOT'
    implementation 'org.bouncycastle:bcprov-jdk15on:1.67'
    implementation 'org.apache.commons:commons-lang3:3.8.1'
    implementation 'com.squareup.okhttp3:okhttp:4.8.1'
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.0'
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.11.0'
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.0'
    implementation 'com.fasterxml.jackson.module:jackson-module-parameter-names:2.11.0'
    implementation 'org.projectlombok:lombok:1.18.12'
    implementation 'org.apache.logging.log4j:log4j-api:2.13.3'
    implementation 'org.apache.logging.log4j:log4j-core:2.13.3'
    implementation 'org.apache.logging.log4j:log4j-slf4j-impl:2.13.3'
}
```

### 配置说明

- 应用配置

```
public class AppConfig {
    // 节点管理服务地址
    private String nodeManagerUrl;
    // 应用Key
    private String appKey;
    // 应用密码
    private String appSecret;
    // 是否加密传输
    private boolean isTransferEncrypt;
}
```

- Http请求配置

```
public class HttpConfig {
	// 连接超时（默认30s）
    private int connectTimeout;
    // 读取超时（默认30s）
    private int readTimeout;
    // 写超时（默认30s）
    private int writeTimeout;
}
```

### 调用示例

完整示例请查看[SDK示例](https://github.com/WeBankFinTech/WeBASE-APP-SDK/blob/main/src/test/java/com/webank/webase/app/sdk/ClientTest.java)。

```java
public class ClientTest {

    // WeBASE-Node-Manager的url
    private static String url = "http://localhost:5001";
    private static String appKey = "RUPCNAsd";
    private static String appSecret = "65KiXNxUpPywVwQxM7SFsMHsKmCbpGrQ";
    private static boolean isTransferEncrypt = true;

    private static AppClient appClient = null;

    public static void main(String[] args) {
        try {
            initClient();
            appRegister();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void initClient() {
        // 未设置httpConfig时，默认http连接均为30s
        HttpConfig httpConfig = new HttpConfig(30, 30, 30);
        appClient = new AppClient(url, appKey, appSecret, isTransferEncrypt, httpConfig);
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
}
```

