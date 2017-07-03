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

package org.openo.nfvo.resmanagement.service.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.service.business.inf.SitesBusiness;
import org.openo.nfvo.resmanagement.service.dao.inf.SitesDao;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sites info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 24, 2016
 */
public class SitesBusinessImpl implements SitesBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(SitesBusinessImpl.class);

    private SitesDao sitesDao;

    private static final String TYPE_ADD = "add";

    private static final String TYPE_UPDATE = "update";

    private static final String TYPE_DELETE = "delete";

    @Override
    public SitesEntity getSite(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=getSite; msg=get error, because id is empty.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.add.id.null"));
        }
        return sitesDao.getSite(id);
    }

    @Override
    public List<SitesEntity> getSites(Map<String, Object> condition) {
        return sitesDao.getSites(condition);
    }

    @Override
    public int deleteSite(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=deleteSite; msg=delete error, because id is empty.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.delete.id.null"));
        }

        return sitesDao.deleteSite(id);
    }

    @Override
    public int addSite(SitesEntity sitesEntity) throws ServiceException {
        LOGGER.info("addSite sitesEntity");
        if(null == sitesEntity) {
            LOGGER.error("function=addSite; msg=add error, because sitesEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.add.entity.null"));
        }
        // if(!StringUtil.checkXss(sitesEntity.getName()) ||
        // !StringUtil.checkXss(sitesEntity.getCountry())
        // || !StringUtil.checkXss(sitesEntity.getLocation())) {
        // LOGGER.error("function=addLocation; msg=add site error, because XSS injection.");
        // throw new ServiceException(
        // ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.add.xss.check"));
        // }
        LOGGER.info("sitesEntity: " + sitesEntity.toString());
        this.checkSite(sitesEntity, TYPE_ADD);
        SitesEntity.dataFramat(sitesEntity);
        LOGGER.info("Add datacenter data into DB.");
        return sitesDao.addSite(sitesEntity);
    }

    private void checkSite(SitesEntity sitesEntity, String type) throws ServiceException {
        if(TYPE_ADD.equals(type)) {
            checkId(sitesEntity.getId());
            checkSiteName(sitesEntity.getName());
            if(!SitesEntity.checkResource(sitesEntity)) {
                LOGGER.error("function=checkRespool; msg=site{} resource error", sitesEntity.toString());
                throw new ServiceException(
                        ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.add.resource.check"));
            }
        }
    }

    private void checkSiteName(String siteName) throws ServiceException {
        Map<String, Object> siteMap = new HashMap<>(10);
        siteMap.put(ParamConstant.PARAM_NAME, siteName);
        List<SitesEntity> siteList = sitesDao.getSites(siteMap);
        if(null != siteList && !siteList.isEmpty()) {
            LOGGER.error("function=checkSiteName; msg=site: {} has already exist.", siteName);
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.add.name.check"));
        }
    }

    private void checkId(String id) throws ServiceException {
        SitesEntity site = sitesDao.getSite(id);
        if(null != site) {
            LOGGER.error("function=checkId; msg=add error, because id is already exist.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.add.id.check"));
        }
    }

    @Override
    public int addSiteSelective(SitesEntity sitesEntity) throws ServiceException {
        if(null == sitesEntity) {
            LOGGER.error("function=addSiteSelective; msg=add error, because sitesEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.add.entity.null"));
        }
        this.checkSite(sitesEntity, TYPE_ADD);

        if(StringUtils.isEmpty(sitesEntity.getId())) {
            sitesEntity.setId(UUID.randomUUID().toString());
        }
        SitesEntity.dataFramat(sitesEntity);
        return sitesDao.addSiteSelective(sitesEntity);
    }

    @Override
    public int updateSiteSelective(SitesEntity sitesEntity) throws ServiceException {
        if(null == sitesEntity) {
            LOGGER.error("function=updateSiteSelective; msg=update error, because sitesEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.update.entity.null"));
        }

        this.checkSite(sitesEntity, TYPE_UPDATE);
        SitesEntity.dataFramat(sitesEntity);
        return sitesDao.updateSiteSelective(sitesEntity);
    }

    @Override
    public int updateSite(SitesEntity sitesEntity) throws ServiceException {
        if(null == sitesEntity) {
            LOGGER.error("function=updateSite; msg=update error, because sitesEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.update.entity.null"));
        }
        this.checkSite(sitesEntity, TYPE_UPDATE);
        SitesEntity.dataFramat(sitesEntity);
        return sitesDao.updateSite(sitesEntity);
    }

    @Override
    public int updateSiteByVimId(SitesEntity sitesEntity) throws ServiceException {
        if(null == sitesEntity) {
            LOGGER.error("function=updateSiteByVimId; msg=update error, because sitesEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.update.entity.null"));
        }
        SitesEntity.dataFramat(sitesEntity);
        return sitesDao.updateSiteByVimId(sitesEntity);
    }

    @Override
    public int updateSiteResource(SitesEntity sitesEntity) throws ServiceException {
        if(null == sitesEntity) {
            LOGGER.error("function=updateSiteResource; msg=update error, because sitesEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.site.update.entity.null"));
        }
        SitesEntity.dataFramat(sitesEntity);
        return sitesDao.updateSiteSelective(sitesEntity);
    }

    public void setSitesDao(SitesDao sitesDao) {
        this.sitesDao = sitesDao;
    }
}
