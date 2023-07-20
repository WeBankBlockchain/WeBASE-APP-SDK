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
import java.util.List;
import lombok.Data;

/**
 * Entity class of front info.
 */
@Data
public class RspFrontInfo {

    private Integer frontId;
    private String nodeId;
    private String frontIp;
    private Integer frontPort;
    private String agency;
    private List<String> groupList;
    /**
     * node version and support version
     */
    private String clientVersion;
    private String supportVersion;
    /**
     * front server version
     */
    private String frontVersion;
    /**
     * sign server version
     */
    private String signVersion;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    /**
     * front status by now
     * 
     * @case1: front's node is abnormal
     * @case2: front's request fail/no response
     */
    private Integer status;


    private Byte runType;
    private Integer agencyId;
    private String agencyName;
    private Integer hostId;
    /**
     * node index bound with front, index is 0, ex: bound with node0
     */
    private Integer hostIndex;
    private String imageTag;
    private String containerName;
    private Integer jsonrpcPort;
    private Integer p2pPort;
    private Integer channelPort;

    private Integer chainId;
    private String chainName;

}
