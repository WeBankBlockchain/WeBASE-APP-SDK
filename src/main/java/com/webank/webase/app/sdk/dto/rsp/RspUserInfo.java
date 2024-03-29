/**
 * Copyright 2014-2021 the original author or authors.
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

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Entity class of user info.
 */
@Data
public class RspUserInfo {

    private Integer userId;
    private String userName;
    private String account;
    private String groupId;
    private String publicKey;
    private String privateKey;
    private Integer userStatus;
    private Integer chainIndex;
    private Integer userType;
    private String address;
    private String signUserId;
    private String appId;
    private Integer hasPk;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

}
