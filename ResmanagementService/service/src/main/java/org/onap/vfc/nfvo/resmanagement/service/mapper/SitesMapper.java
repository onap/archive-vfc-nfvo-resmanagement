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

package org.onap.vfc.nfvo.resmanagement.service.mapper;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.SitesEntity;

/**
 * ResPool info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 24, 2016
 */
public interface SitesMapper {

    /**
     * It is used to get Sites info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    SitesEntity getSite(String id);

    /**
     *
     * It is used to get Sites info.<br>
     *
     * @param map
     * @return
     * @since  VFC 1.0
     */
    List<SitesEntity> getSites(Map<String, Object> map);

    /**
     * It is used to get Sites info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    int deleteSite(String id);

    /**
     *
     * It is used to delete Site info.<br>
     *
     * @param entity
     * @return
     * @since  VFC 1.0
     */
    int addSite(SitesEntity entity);

    /**
     *
     * It is used to add Sites info.<br>
     *
     * @param entity
     * @return
     * @since  VFC 1.0
     */
    int addSiteSelective(SitesEntity entity);

    /**
     *
     * It is used to add Site info.<br>
     *
     * @param entity
     * @return
     * @since  VFC 1.0
     */
    int updateSiteSelective(SitesEntity entity);

    /**
     *
     * It is used to update Sites info.<br>
     *
     * @param entity
     * @return
     * @since  VFC 1.0
     */
    int updateSite(SitesEntity entity);

    /**
     *
     * It is used to update Site info.<br>
     *
     * @param entity
     * @return
     * @since  VFC 1.0
     */
    int updateSiteByVimId(SitesEntity entity);
}
