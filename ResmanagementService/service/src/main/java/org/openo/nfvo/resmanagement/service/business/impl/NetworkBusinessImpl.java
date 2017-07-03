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
import org.openo.nfvo.resmanagement.service.business.inf.NetworkBusiness;
import org.openo.nfvo.resmanagement.service.dao.inf.NetworkDao;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Network business implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public class NetworkBusinessImpl implements NetworkBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkBusinessImpl.class);

    private NetworkDao networkDao;

    @Override
    public NetworkEntity getNetwork(String id) {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=getNetwork; msg=get error, because id is empty.");
            return null;
        }
        return networkDao.getNetwork(id);
    }

    @Override
    public List<NetworkEntity> getNetworks(Map<String, Object> condition) {
        return networkDao.getNetworks(condition);
    }

    @Override
    public int deleteNetwork(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=deleteNetwork; msg=delete error, because id is empty.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.network.delete.id.null"));
        }
        return networkDao.deleteNetwork(id);
    }

    @Override
    public int addNetwork(NetworkEntity networkEntity) throws ServiceException {
        if(null == networkEntity) {
            LOGGER.error("function=addNetwork; msg=add error, because networkEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.network.add.entity.null"));
        }
        if(!checkId(networkEntity.getId())) {
            return networkDao.updateNetworkSelective(networkEntity);
        }
        if(StringUtils.isEmpty(networkEntity.getId())) {
            networkEntity.setId(UUID.randomUUID().toString());
        }
        return networkDao.addNetwork(networkEntity);
    }

    @Override
    public int addNetworkSelective(NetworkEntity networkEntity) throws ServiceException {
        if(null == networkEntity) {
            LOGGER.error("function=addNetworkSelective; msg=add error, because networkEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.network.add.entity.null"));
        }
        if(!checkId(networkEntity.getId())) {
            return networkDao.updateNetworkSelective(networkEntity);
        }
        if(StringUtils.isEmpty(networkEntity.getId())) {
            networkEntity.setId(UUID.randomUUID().toString());
        }
        return networkDao.addNetworkSelective(networkEntity);
    }

    private boolean checkId(String id) {
        NetworkEntity network = networkDao.getNetwork(id);
        if(null == network) {
            return true;
        }
        return false;
    }

    @Override
    public int updateNetworkSelective(NetworkEntity networkEntity) throws ServiceException {
        if(null == networkEntity) {
            LOGGER.error("function=updateNetworkSelective; msg=update error, because networkEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.network.update.entity.null"));
        }
        return networkDao.updateNetworkSelective(networkEntity);
    }

    @Override
    public int updateNetwork(NetworkEntity networkEntity) throws ServiceException {
        if(null == networkEntity) {
            LOGGER.error("function=updateNetwork; msg=update error, because networkEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.network.update.entity.null"));
        }
        return networkDao.updateNetwork(networkEntity);
    }

    @Override
    public int updateNetworkByVimId(NetworkEntity networkEntity) throws ServiceException {
        if(null == networkEntity) {
            LOGGER.error("function=updateNetworkByVimId; msg=update error, because networkEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.network.update.entity.null"));
        }
        return networkDao.updateNetworkByVimId(networkEntity);
    }

    @Override
    public int deleteNetworkByVimId(String vimId) throws ServiceException {
        if(StringUtils.isEmpty(vimId)) {
            LOGGER.error("function=deleteNetworkByVimId; msg=delete error, because VimId is empty.");
            throw new ServiceException(ResourceUtil.getMessage(""));
        }
        return networkDao.deleteNetworkByVimId(vimId);
    }

    public void setNetworkDao(NetworkDao networkDao) {
        this.networkDao = networkDao;
    }

}
