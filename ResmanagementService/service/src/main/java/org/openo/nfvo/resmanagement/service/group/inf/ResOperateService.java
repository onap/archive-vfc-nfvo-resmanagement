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

package org.openo.nfvo.resmanagement.service.group.inf;

import org.openo.baseservice.remoteservice.exception.ServiceException;

import net.sf.json.JSONObject;

/**
 * resource operational service class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public interface ResOperateService {

    /**
     * Update iresource.<br>
     *
     * @param tenantId
     * @param vimId
     * @param header
     * @throws ServiceException
     * @since NFVO 0.5
     */
    void updateIRes(String tenantId, String vimId, JSONObject header) throws ServiceException;

    /**
     * Update all iResource.<br>
     *
     * @throws ServiceException
     * @since NFVO 0.5
     */
    void updateAllIRes() throws ServiceException;

    /**
     * Add iResource.<br>
     *
     * @param tenantId
     * @param vimId
     * @param header
     * @throws ServiceException
     * @since NFVO 0.5
     */
    void addIRes(String tenantId, String vimId, JSONObject header) throws ServiceException;

    /**
     * Delete iResource.<br>
     *
     * @param vimId
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    int deleteIRes(String vimId) throws ServiceException;

    /**
     * <br>
     * 
     * @param operateType
     * @param vimId
     * @throws ServiceException
     * @since NFVO 0.5
     */
    void sendMsgMonitor(String operateType, String vimId) throws ServiceException;
}
