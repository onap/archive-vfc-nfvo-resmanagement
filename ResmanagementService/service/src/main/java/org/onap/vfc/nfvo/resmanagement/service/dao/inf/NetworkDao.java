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

package org.onap.vfc.nfvo.resmanagement.service.dao.inf;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.NetworkEntity;

/**
 *
 * Network DOA Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     VFC 1.0  Sep 10, 2016
 */
public interface NetworkDao {

    /**
     *
     * Get Network Details.<br>
     *
     * @param id
     * @return
     * @since  VFC 1.0
     */
    NetworkEntity getNetwork(String id);

    /**
     *
     * Get details of available networks.<br>
     *
     * @param condition
     * @return
     * @since  VFC 1.0
     */
    List<NetworkEntity> getNetworks(Map<String, Object> condition);

    /**
     *
     * Delete Network.<br>
     *
     * @param id
     * @return
     * @since  VFC 1.0
     */
    int deleteNetwork(String id);

    /**
     *
     * Delete network by  VIM ID.<br>
     *
     * @param vimId
     * @return
     * @since  VFC 1.0
     */
    int deleteNetworkByVimId(String vimId);

    /**
     *
     * Add Network.<br>
     *
     * @param networkEntity
     * @return
     * @since  VFC 1.0
     */
    int addNetwork(NetworkEntity networkEntity);

    /**
     *
     * Add selective network.<br>
     *
     * @param networkEntity
     * @return
     * @since  VFC 1.0
     */
    int addNetworkSelective(NetworkEntity networkEntity);

    /**
     *
     * Update selective network.<br>
     *
     * @param networkEntity
     * @return
     * @since  VFC 1.0
     */
    int updateNetworkSelective(NetworkEntity networkEntity);

    /**
     *
     * Update network.<br>
     *
     * @param networkEntity
     * @return
     * @since  VFC 1.0
     */
    int updateNetwork(NetworkEntity networkEntity);

    /**
     *
     * Update network by VIM ID.<br>
     *
     * @param networkEntity
     * @return
     * @since  VFC 1.0
     */
    int updateNetworkByVimId(NetworkEntity networkEntity);

}
