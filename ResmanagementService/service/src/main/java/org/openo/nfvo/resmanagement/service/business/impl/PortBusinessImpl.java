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
import org.openo.nfvo.resmanagement.service.business.inf.PortBusiness;
import org.openo.nfvo.resmanagement.service.dao.inf.PortDao;
import org.openo.nfvo.resmanagement.service.entity.PortEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Port business implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public class PortBusinessImpl implements PortBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortBusinessImpl.class);

    private PortDao portDao;

    @Override
    public PortEntity getPort(String id) {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=getPort; msg=get error, because id is empty.");
            return null;
        }
        return portDao.getPort(id);
    }

    @Override
    public List<PortEntity> getPorts(Map<String, Object> condition) {
        return portDao.getPorts(condition);
    }

    @Override
    public int deletePort(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=deletePort; msg=delete error, because id is empty.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.port.delete.id.null"));
        }
        PortEntity rp = portDao.getPort(id);
        if(null == rp) {
            return portDao.deletePort(id);
        }

        return portDao.deletePort(id);
    }

    @Override
    public int addPort(PortEntity portEntity) throws ServiceException {
        if(null == portEntity) {
            LOGGER.error("function=addPort; msg=add error, because portEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.port.add.entity.null"));
        }

        if(!checkId(portEntity.getId())) {
            return portDao.updatePortSelective(portEntity);
        }
        if(StringUtils.isEmpty(portEntity.getId())) {
            portEntity.setId(UUID.randomUUID().toString());
        }
        return portDao.addPort(portEntity);
    }

    @Override
    public int addPortSelective(PortEntity portEntity) throws ServiceException {
        if(null == portEntity) {
            LOGGER.error("function=addPortSelective; msg=add error, because portEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.port.add.entity.null"));
        }
        if(!checkId(portEntity.getId())) {
            return portDao.updatePortSelective(portEntity);
        }

        if(StringUtils.isEmpty(portEntity.getId())) {
            portEntity.setId(UUID.randomUUID().toString());
        }
        return portDao.addPortSelective(portEntity);
    }

    private boolean checkId(String id) {
        PortEntity respool = portDao.getPort(id);
        if(null == respool) {
            return true;
        }
        return false;
    }

    @Override
    public int updatePortSelective(PortEntity portEntity) throws ServiceException {
        if(null == portEntity) {
            LOGGER.error("function=updatePortSelective; msg=update error, because portEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.port.update.entity.null"));
        }

        return portDao.updatePortSelective(portEntity);
    }

    @Override
    public int updatePort(PortEntity portEntity) throws ServiceException {
        if(null == portEntity) {
            LOGGER.error("function=updatePort; msg=update error, because portEntity is null.");
            throw new ServiceException("update error, because portEntity is null.");
        }

        return portDao.updatePort(portEntity);
    }

    @Override
    public int updatePortByVimId(PortEntity portEntity) throws ServiceException {
        if(null == portEntity) {
            LOGGER.error("function=updatePortByVimId; msg=update error, because portEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.port.update.entity.null"));
        }
        return portDao.updatePortByVimId(portEntity);
    }

    @Override
    public int deletePortByVimId(String vimId) throws ServiceException {
        if(StringUtils.isEmpty(vimId)) {
            LOGGER.error("function=deletePortByVimId; msg=delete error, because VimId is empty.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.port.delete.vimid.check"));
        }
        return portDao.deletePortByVimId(vimId);
    }

    public void setPortDao(PortDao portDao) {
        this.portDao = portDao;
    }

}
