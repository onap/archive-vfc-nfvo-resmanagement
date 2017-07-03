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

package org.openo.nfvo.resmanagement.service.group.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.service.dao.inf.VirtualLinkDao;
import org.openo.nfvo.resmanagement.service.entity.VirtualLinkEntity;
import org.openo.nfvo.resmanagement.service.group.inf.VirtualLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Oct 27, 2016
 */
public class VirtualLinkServiceImpl implements VirtualLinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualLinkServiceImpl.class);

    private VirtualLinkDao virtualLinkDao;

    /**
     * <br>
     * 
     * @param virtualLinkEntity
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @Override
    public JSONObject addVl(VirtualLinkEntity virtualLinkEntity) throws ServiceException {
        if(!checkId(virtualLinkEntity.getId())) {
            LOGGER.error("function=addVl; msg=add error, because id is already exist.");
            throw new ServiceException(ResourceUtil
                    .getMessage("org.openo.nfvo.resmanagement.service.group.impl.VirtualLinkServiceImpl.add.id.check"));
        }
        if(StringUtils.isEmpty(virtualLinkEntity.getId())) {
            virtualLinkEntity.setId(UUID.randomUUID().toString());
        }
        int result = virtualLinkDao.addVl(virtualLinkEntity);
        JSONObject restJson = new JSONObject();
        if(result > 0) {
            restJson.put("id", virtualLinkEntity.getId());
            restJson.put("name", virtualLinkEntity.getName());
        } else {
            LOGGER.error("function=addVl; msg=add vl into DB error.");
            restJson.put("message", "Add Vl into DB error.");
        }
        return restJson;

    }

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since NFVO 0.5
     */
    private boolean checkId(String id) {
        VirtualLinkEntity vl = virtualLinkDao.getVl(id);
        if(null == vl) {
            return true;
        }
        return false;
    }

    /**
     * <br>
     * 
     * @param map
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @Override
    public List<VirtualLinkEntity> getList(Map<String, Object> map) throws ServiceException {
        return virtualLinkDao.getVls(map);
    }

    /**
     * <br>
     * 
     * @param id
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @Override
    public int delete(String id) throws ServiceException {
        return virtualLinkDao.deleteVlById(id);
    }

    public void setVirtualLinkDao(VirtualLinkDao virtualLinkDao) {
        this.virtualLinkDao = virtualLinkDao;
    }
}
