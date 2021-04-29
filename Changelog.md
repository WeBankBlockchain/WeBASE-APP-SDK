
### v1.5.1(2021-05-20)

**Add**
- 新增应用集成SDK，方便应用接入。



**兼容性**

- WeBASE-Node-Manager v1.5.0+

- `v1.5.1`及其以上版本，SDK应用配置AppConfig的属性`isTransferEncrypt`需和WeBASE-Node-Manager的配置文件`/conf/application.yml`下的配置`constant.isTransferEncrypt`相同。

- 如果`v1.5.0`需要使用SDK，SDK应用配置AppConfig的属性`isTransferEncrypt`需设置为`false`。`v1.5.1`及其以上版本新增的接口调用不了。

  详细了解，请阅读[**技术文档**](https://webasedoc.readthedocs.io/zh_CN/latest/)。

