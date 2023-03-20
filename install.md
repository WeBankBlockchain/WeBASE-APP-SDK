# SDK构建

## 1. 拉取代码

执行命令：
```shell
git clone https://github.com/WeBankBlockchain/WeBASE-APP-SDK.git
```
进入目录：

```shell
cd WeBASE-APP-SDK
```

## 2. 编译代码

方式一：如果服务器已安装Gradle，且版本为Gradle-4.10或以上

```shell
gradle build -x test
```

方式二：如果服务器未安装Gradle，或者版本不是Gradle-4.10或以上，使用gradlew编译

```shell
chmod +x ./gradlew && ./gradlew build -x test
```

构建完成后，会在根目录下生成已编译目录dist，目录下会生成相应jar包。

