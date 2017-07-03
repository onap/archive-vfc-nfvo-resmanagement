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
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;

/**
 * DC info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 24, 2016
 */
public interface SitesBusiness {

    /**
     * It is used to get Site info.<br/>
     *
     * @param id
     * @return The get result
     * @throws ServiceException When get failed.
     * @since NFVO 0.5
     */
    SitesEntity getSite(String id) throws ServiceException;

    /**
     * It is used to get Sites info.<br/>
     *
     * @param condition
     * @return The get result
     * @since NFVO 0.5
     */
    List<SitesEntity> getSites(Map<String, Object> condition);

    /**
     * It is used to delete Site info.<br/>
     *
     * @param id
     * @return The delete result
     * @throws ServiceException When delete failed.
     * @since NFVO 0.5
     */
    int deleteSite(String id) throws ServiceException;

    /**
     * It is used to add Site info.<br/>
     *
     * @param sitesEntity
     * @return The add result
     * @throws ServiceException When add failed.
     * @since NFVO 0.5
     */
    int addSite(SitesEntity sitesEntity) throws ServiceException;

    /**
     * It is used to add Site info.<br/>
     *
     * @param sitesEntity
     * @return The add result
     * @throws ServiceException When add failed.
     * @since NFVO 0.5
     */
    int addSiteSelective(SitesEntity sitesEntity) throws ServiceException;

    /**
     * It is used to update Site info.<br/>
     *
     * @param sitesEntity
     * @return The update result
     * @throws ServiceException When update failed.
     * @since NFVO 0.5
     */
    int updateSiteSelective(SitesEntity sitesEntity) throws ServiceException;

    /**
     * It is used to create update Site info.<br/>
     *
     * @param sitesEntity
     * @return The update result
     * @throws ServiceException When update failed.
     * @since NFVO 0.5
     */
    int updateSite(SitesEntity sitesEntity) throws ServiceException;

    /**
     * It is used to update Site info.<br/>
     *
     * @param sitesEntity
     * @return The update result
     * @throws ServiceException When update failed.
     * @since NFVO 0.5
     */
    int updateSiteByVimId(SitesEntity sitesEntity) throws ServiceException;

    /**
     * It is used to update Site info.<br/>
     *
     * @param sitesEntity
     * @return The update result
     * @throws ServiceException When update failed.
     * @since NFVO 0.5
     */
    int updateSiteResource(SitesEntity sitesEntity) throws ServiceException;

}
