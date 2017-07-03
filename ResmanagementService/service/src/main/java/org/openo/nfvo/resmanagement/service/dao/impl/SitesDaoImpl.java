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

package org.openo.nfvo.resmanagement.service.dao.impl;

import java.util.List;
import java.util.Map;

import org.openo.nfvo.resmanagement.service.dao.impl.AbstractDao;
import org.openo.nfvo.resmanagement.service.dao.inf.SitesDao;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;
import org.openo.nfvo.resmanagement.service.mapper.SitesMapper;

/**
 * DC interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 25, 2016
 */
public class SitesDaoImpl extends AbstractDao implements SitesDao {

    @Override
    public SitesEntity getSite(String id) {
        return getMapperManager(SitesMapper.class).getSite(id);
    }

    @Override
    public List<SitesEntity> getSites(Map<String, Object> condition) {
        return getMapperManager(SitesMapper.class).getSites(condition);
    }

    @Override
    public int deleteSite(String id) {
        return getMapperManager(SitesMapper.class).deleteSite(id);
    }

    @Override
    public int addSite(SitesEntity sitesEntity) {
        return getMapperManager(SitesMapper.class).addSite(sitesEntity);
    }

    @Override
    public int addSiteSelective(SitesEntity sitesEntity) {
        return getMapperManager(SitesMapper.class).addSiteSelective(sitesEntity);
    }

    @Override
    public int updateSiteSelective(SitesEntity sitesEntity) {
        return getMapperManager(SitesMapper.class).updateSiteSelective(sitesEntity);
    }

    @Override
    public int updateSite(SitesEntity sitesEntity) {
        return getMapperManager(SitesMapper.class).updateSite(sitesEntity);
    }

    @Override
    public int updateSiteByVimId(SitesEntity sitesEntity) {
        return getMapperManager(SitesMapper.class).updateSiteByVimId(sitesEntity);
    }

}
