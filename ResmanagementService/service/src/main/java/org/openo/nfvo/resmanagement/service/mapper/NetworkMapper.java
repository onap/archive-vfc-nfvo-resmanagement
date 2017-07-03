/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.nfvo.resmanagement.service.mapper;

import java.util.List;
import java.util.Map;

import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;

/**
 *
 * Network Mapper Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public interface NetworkMapper {

    /**
     *
     * Get network details.<br>
     *
     * @param id
     * @return
     * @since  NFVO 0.5
     */
    NetworkEntity getNetwork(String id);

    /**
     *
     * Get details of networks.<br>
     *
     * @param condition
     * @return
     * @since  NFVO 0.5
     */
    List<NetworkEntity> getNetworks(Map<String, Object> condition);

    /**
     *
     * Delete network.<br>
     *
     * @param id
     * @return
     * @since  NFVO 0.5
     */
    int deleteNetwork(String id);

    /**
     *
     * Delete network by VIM ID.<br>
     *
     * @param vimId
     * @return
     * @since  NFVO 0.5
     */
    int deleteNetworkByVimId(String vimId);

    /**
     *
     * Add network.<br>
     *
     * @param networkEntity
     * @return
     * @since  NFVO 0.5
     */
    int addNetwork(NetworkEntity networkEntity);

    /**
     *
     * Add selective network.<br>
     *
     * @param networkEntity
     * @return
     * @since  NFVO 0.5
     */
    int addNetworkSelective(NetworkEntity networkEntity);

    /**
     *
     * Update selective network.<br>
     *
     * @param networkEntity
     * @return
     * @since  NFVO 0.5
     */
    int updateNetworkSelective(NetworkEntity networkEntity);

    /**
     *
     * Update network.<br>
     *
     * @param networkEntity
     * @return
     * @since  NFVO 0.5
     */
    int updateNetwork(NetworkEntity networkEntity);

    /**
     *
     * Update network by VIM Id.<br>
     *
     * @param networkEntity
     * @return
     * @since  NFVO 0.5
     */
    int updateNetworkByVimId(NetworkEntity networkEntity);

}
