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

package org.onap.vfc.nfvo.resmanagement.service.base.openstack.inf;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.PortEntity;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

import net.sf.json.JSONObject;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 25, 2016
 */
public interface Port extends InterfaceResManagement {

    /**
     * <br/>
     *
     * @param jsonObject
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    int updateStatusByVimId(JSONObject jsonObject) throws ServiceException;

    /**
     * <br/>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    List<PortEntity> getList(Map<String, Object> condition) throws ServiceException;

    /**
     * <br/>
     *
     * @param resPoolEntity
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    int update(PortEntity resPoolEntity) throws ServiceException;

    /**
     * <br>
     *
     * @param portEntity
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    int add(PortEntity portEntity) throws ServiceException;
}
