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

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.service.business.inf.HostBusiness;
import org.openo.nfvo.resmanagement.service.dao.inf.HostDao;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Host business implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class HostBusinessImpl implements HostBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostBusinessImpl.class);

    private HostDao hostDao;

    @Override
    public HostEntity getHost(String id) {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=getHost; msg=get error, because id is empty.");
            return null;
        }
        return hostDao.getHost(id);
    }

    @Override
    public List<HostEntity> getHosts(Map<String, Object> condition) {
        return hostDao.getHosts(condition);
    }

    @Override
    public int deleteHost(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=deleteHost; msg=delete error, because id is empty.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.host.delete.id.null"));
        }
        HostEntity rp = hostDao.getHost(id);
        if(null == rp) {
            return hostDao.deleteHost(id);
        }

        return hostDao.deleteHost(id);
    }

    @Override
    public int addHost(HostEntity hostEntity) throws ServiceException {
        if(null == hostEntity) {
            LOGGER.error("function=addHost; msg=add error, because hostEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.host.add.entity.null"));
        }

        if(!checkId(hostEntity.getId())) {
            LOGGER.error("function=addHost; msg=add error, because id is already exist.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.host.add.id.check"));
        }
        if(StringUtils.isEmpty(hostEntity.getId())) {
            hostEntity.setId(UUID.randomUUID().toString());
        }
        return hostDao.addHost(hostEntity);
    }

    @Override
    public int addHostSelective(HostEntity hostEntity) throws ServiceException {
        if(null == hostEntity) {
            LOGGER.error("function=addHostSelective; msg=add error, because hostEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.host.add.entity.null"));
        }
        if(!checkId(hostEntity.getId())) {
            LOGGER.error("function=addHostSelective; msg=add error, because id is allready exist.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.host.add.id.check"));
        }

        if(StringUtils.isEmpty(hostEntity.getId())) {
            hostEntity.setId(UUID.randomUUID().toString());
        }
        return hostDao.addHostSelective(hostEntity);
    }

    private boolean checkId(String id) {
        HostEntity respool = hostDao.getHost(id);
        if(null == respool) {
            return true;
        }
        return false;
    }

    @Override
    public int updateHostSelective(HostEntity hostEntity) throws ServiceException {
        if(!checkId(hostEntity.getId())) {
            return hostDao.updateHostSelective(hostEntity);
        } else {
            return addHostSelective(hostEntity);
        }
    }

    @Override
    public int updateHost(HostEntity hostEntity) throws ServiceException {
        if(null == hostEntity) {
            LOGGER.error("function=updateHost; msg=update error, because hostEntity is null.");
            throw new ServiceException("update error, because hostEntity is null.");
        }

        return hostDao.updateHost(hostEntity);
    }

    @Override
    public int updateHostByVimId(HostEntity hostEntity) throws ServiceException {
        if(null == hostEntity) {
            LOGGER.error("function=updateHostByVimId; msg=update error, because hostEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.host.update.entity.null"));
        }
        return hostDao.updateHostByVimId(hostEntity);
    }

    @Override
    public int deleteHostByVimId(String vimId) throws ServiceException {
        if(StringUtils.isEmpty(vimId)) {
            LOGGER.error("function=deleteHostByVimId; msg=delete error, because VimId is empty.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.host.delete.vimid.check"));
        }
        return hostDao.deleteHostByVimId(vimId);
    }

    public void setHostDao(HostDao hostDao) {
        this.hostDao = hostDao;
    }

}
