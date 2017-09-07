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

import org.onap.vfc.nfvo.resmanagement.service.entity.SitesEntity;

/**
 * Sites info interface. <br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 24, 2016
 */
public interface SitesDao {

    /**
     * It is used to get Sites info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    SitesEntity getSite(String id);

    /**
     * It is used to get Sites info.<br/>
     *
     * @param condition
     * @return The get result
     * @since VFC 1.0
     */
    List<SitesEntity> getSites(Map<String, Object> condition);

    /**
     * It is used to get Sites info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    int deleteSite(String id);

    /**
     * It is used to delete Sites info.<br/>
     *
     * @param sitesEntity
     * @return The delete result
     * @since VFC 1.0
     */
    int addSite(SitesEntity sitesEntity);

    /**
     * It is used to add Sites info.<br/>
     *
     * @param sitesEntity
     * @return The add result
     * @since VFC 1.0
     */
    int addSiteSelective(SitesEntity sitesEntity);

    /**
     * It is used to add Sites info.<br/>
     *
     * @param sitesEntity
     * @return The add result
     * @since VFC 1.0
     */
    int updateSiteSelective(SitesEntity sitesEntity);

    /**
     * It is used to update Sites info.<br/>
     *
     * @param sitesEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updateSite(SitesEntity sitesEntity);

    /**
     * It is used to update Sites info.<br/>
     *
     * @param sitesEntity
     * @return The update result
     * @since VFC 1.0
     */
    int updateSiteByVimId(SitesEntity sitesEntity);
}
