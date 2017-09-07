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

import org.onap.vfc.nfvo.resmanagement.service.entity.PortEntity;

/**
 * Port info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 24, 2016
 */
public interface PortDao {

    /**
     * It is used to get Port info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    PortEntity getPort(String id);

    /**
     * It is used to get Ports info.<br/>
     *
     * @param condition
     * @return The get result
     * @since VFC 1.0
     */
    List<PortEntity> getPorts(Map<String, Object> condition);

    /**
     * It is used to delete Ports info.<br/>
     *
     * @param id
     * @return The delete result
     * @since VFC 1.0
     */
    int deletePort(String id);

    /**
     * It is used to delete Ports info.<br/>
     *
     * @param vimId
     * @return The delete result
     * @since VFC 1.0
     */
    int deletePortByVimId(String vimId);

    /**
     * It is used to add Ports info. <br/>
     *
     * @param portEntity
     * @return The add result
     * @since VFC 1.0
     */
    int addPort(PortEntity portEntity);

    /**
     * It is used to add Ports info. <br/>
     *
     * @param portEntity
     * @return The add result
     * @since VFC 1.0
     */
    int addPortSelective(PortEntity portEntity);

    /**
     * It is used to update Ports info. <br/>
     *
     * @param portEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updatePortSelective(PortEntity portEntity);

    /**
     * It is used to update Ports info. <br/>
     *
     * @param portEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updatePort(PortEntity portEntity);

    /**
     * It is used to update Ports info. <br/>
     *
     * @param portEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updatePortByVimId(PortEntity portEntity);
}
