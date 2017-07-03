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

package org.openo.nfvo.resmanagement.service.business.inf;

import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;

/**
 * Host Buisiness Class.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 31, 2016
 */
public interface HostBusiness {

    /**
     *
     * Get host.<br>
     *
     * @param id
     * @return
     * @since  NFVO 0.5
     */
    HostEntity getHost(String id);

    /**
     *
     * Get details of hosts.<br>
     *
     * @param condition
     * @return
     * @since  NFVO 0.5
     */
    List<HostEntity> getHosts(Map<String, Object> condition);

    /**
     *
     * Delete host.<br>
     *
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int deleteHost(String id) throws ServiceException;

    /**
     *
     * Delete host by VIM Id.<br>
     *
     * @param vimId
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int deleteHostByVimId(String vimId) throws ServiceException;

    /**
     *
     * Add host.<br>
     *
     * @param hostEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addHost(HostEntity hostEntity) throws ServiceException;

    /**
     *
     * Add selective host.<br>
     *
     * @param hostEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addHostSelective(HostEntity hostEntity) throws ServiceException;

    /**
     *
     * Update selective host.<br>
     *
     * @param hostEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateHostSelective(HostEntity hostEntity) throws ServiceException;

    /**
     *
     * Update host.<br>
     *
     * @param hostEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateHost(HostEntity hostEntity) throws ServiceException;

    /**
     *
     * Update host by VIM Id.<br>
     *
     * @param hostEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateHostByVimId(HostEntity hostEntity) throws ServiceException;
}
