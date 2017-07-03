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

package org.openo.nfvo.resmanagement.service.base.openstack.inf;

import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;

import net.sf.json.JSONObject;

/**
 *
 * Network class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public interface Network extends InterfaceResManagement {

    /**
     *
     * Update status by VIM Id.<br>
     *
     * @param jsonObject
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateStatusByVimId(JSONObject jsonObject) throws ServiceException;

    /**
     *
     * Get list of networks.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    List<NetworkEntity> getList(Map<String, Object> condition) throws ServiceException;

    /**
     * <br>
     *
     * @param entity
     * @return
     * @since NFVO 0.5
     */
    int add(NetworkEntity entity) throws ServiceException;

}
