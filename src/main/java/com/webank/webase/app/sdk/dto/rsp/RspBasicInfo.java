/**
 * Copyright 2014-2021  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.app.sdk.dto.rsp;

import lombok.Data;

/**
 * Entity class of basic info.
 *
 */
@Data
public class RspBasicInfo {
    private Integer encryptType;
    /**
     * 3.0 fisco中，多个节点的 sslCryptoType 可能不一致，但是由于获取basicInfo，默认取第一个
     */
    private Integer sslCryptoType;
    /**
     * 3.0 fisco中，多个节点的 binaryVersion 可能不一致，但是由于获取basicInfo，默认取第一个
     */
    private String fiscoBcosVersion;
    private String webaseVersion;
}
