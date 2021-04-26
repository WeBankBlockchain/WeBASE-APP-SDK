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

import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Entity class of group info.
 */
@Data
public class RspGroupInfo {

    private Integer groupId;
    private String groupName;
    private Integer groupStatus;
    private Integer nodeCount;
    private BigInteger latestBlock = BigInteger.ZERO;
    private BigInteger transCount = BigInteger.ZERO;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String description;
    private Integer groupType;
    private String groupTimestamp;
    private String nodeIdList;
    private Integer chainId;
    private String chainName;

}
