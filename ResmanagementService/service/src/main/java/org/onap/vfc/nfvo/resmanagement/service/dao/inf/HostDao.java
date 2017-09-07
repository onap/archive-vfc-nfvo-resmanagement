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

import org.onap.vfc.nfvo.resmanagement.service.entity.HostEntity;

/**
 * Host info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 24, 2016
 */
public interface HostDao {

    /**
     * It is used to get Host info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    HostEntity getHost(String id);

    /**
     * It is used to get Hosts info.<br/>
     *
     * @param condition
     * @return The get result
     * @since VFC 1.0
     */
    List<HostEntity> getHosts(Map<String, Object> condition);

    /**
     * It is used to delete Hosts info.<br/>
     *
     * @param id
     * @return The delete result
     * @since VFC 1.0
     */
    int deleteHost(String id);

    /**
     * It is used to delete Hosts info.<br/>
     *
     * @param vimId
     * @return The delete result
     * @since VFC 1.0
     */
    int deleteHostByVimId(String vimId);

    /**
     * It is used to add Hosts info. <br/>
     *
     * @param hostEntity
     * @return The add result
     * @since VFC 1.0
     */
    int addHost(HostEntity hostEntity);

    /**
     * It is used to add Hosts info. <br/>
     *
     * @param hostEntity
     * @return The add result
     * @since VFC 1.0
     */
    int addHostSelective(HostEntity hostEntity);

    /**
     * It is used to update Hosts info. <br/>
     *
     * @param hostEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updateHostSelective(HostEntity hostEntity);

    /**
     * It is used to update Hosts info. <br/>
     *
     * @param hostEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updateHost(HostEntity hostEntity);

    /**
     * It is used to update Hosts info. <br/>
     *
     * @param hostEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updateHostByVimId(HostEntity hostEntity);
}
