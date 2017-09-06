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

import org.onap.vfc.nfvo.resmanagement.service.entity.PortEntity;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

/**
 * Port Buisiness Class.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 31, 2016
 */
public interface PortBusiness {

    /**
     *
     * Get Port.<br>
     *
     * @param id
     * @return
     * @since  NFVO 0.5
     */
    PortEntity getPort(String id);

    /**
     *
     * Get details of ports.<br>
     *
     * @param condition
     * @return
     * @since  NFVO 0.5
     */
    List<PortEntity> getPorts(Map<String, Object> condition);

    /**
     *
     * Delete Port.<br>
     *
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int deletePort(String id) throws ServiceException;

    /**
     *
     * Delete port by VIM ID.<br>
     *
     * @param vimId
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int deletePortByVimId(String vimId) throws ServiceException;

    /**
     *
     * Add port.<br>
     *
     * @param portEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addPort(PortEntity portEntity) throws ServiceException;

    /**
     *
     * Add selective port.<br>
     *
     * @param portEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addPortSelective(PortEntity portEntity) throws ServiceException;

    /**
     *
     * Update selective port.<br>
     *
     * @param portEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updatePortSelective(PortEntity portEntity) throws ServiceException;

    /**
     *
     * Update port.<br>
     *
     * @param portEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updatePort(PortEntity portEntity) throws ServiceException;

    /**
     *
     * Update port by VIM Id.<br>
     *
     * @param portEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updatePortByVimId(PortEntity portEntity) throws ServiceException;
}
