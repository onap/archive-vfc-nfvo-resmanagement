/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.onap.vfc.nfvo.resmanagement.service.business.inf;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.NetworkEntity;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

/**
 *
 * Network Business class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public interface NetworkBusiness {

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
     * Get details of available networks.<br>
     *
     * @param condition
     * @return
     * @since  NFVO 0.5
     */
    List<NetworkEntity> getNetworks(Map<String, Object> condition);

    /**
     *
     * Delete Network.<br>
     *
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int deleteNetwork(String id) throws ServiceException;

    /**
     *
     * Delete network by Virtual Infrastructure Manager ID.<br>
     *
     * @param vimId
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int deleteNetworkByVimId(String vimId) throws ServiceException;

    /**
     *
     * Add Network.<br>
     *
     * @param networkEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addNetwork(NetworkEntity networkEntity) throws ServiceException;

    /**
     *
     * Add Selective Network.<br>
     *
     * @param networkEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addNetworkSelective(NetworkEntity networkEntity) throws ServiceException;

    /**
     *
     * Update Selective Network.<br>
     *
     * @param networkEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateNetworkSelective(NetworkEntity networkEntity) throws ServiceException;

    /**
     *
     * Update Network.<br>
     *
     * @param networkEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateNetwork(NetworkEntity networkEntity) throws ServiceException;

    /**
     *
     * Update network by Virtual Infrastructure Manager ID.<br>
     *
     * @param networkEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateNetworkByVimId(NetworkEntity networkEntity) throws ServiceException;

}
