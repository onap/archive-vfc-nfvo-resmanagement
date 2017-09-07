/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.onap.vfc.nfvo.resmanagement.service.group.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.onap.vfc.nfvo.resmanagement.common.ResourceUtil;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.VnfDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.VnfEntity;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.VnfInfoService;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.VnfService;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version VFC 1.0 Oct 28, 2016
 */
public class VnfServiceImpl implements VnfService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VnfServiceImpl.class);

    private VnfDao vnfDao;

    private VnfInfoService vnfInfoService;

    /**
     * <br>
     * 
     * @param vnfEntity
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    @Override
    public JSONObject addVnf(VnfEntity vnfEntity) throws ServiceException {
        if(!checkId(vnfEntity.getId())) {
            LOGGER.error("function=addVnf; msg=add error, because id is already exist.");
            throw new ServiceException(ResourceUtil
                    .getMessage("org.onap.vfc.nfvo.resmanagement.service.group.impl.VnfServiceImpl.add.id.check"));
        }
        if(StringUtils.isEmpty(vnfEntity.getId())) {
            vnfEntity.setId(UUID.randomUUID().toString());
        }
        int result = vnfDao.addVnf(vnfEntity);
        JSONObject restJson = new JSONObject();
        if(result > 0) {
            restJson.put("id", vnfEntity.getId());
            restJson.put("name", vnfEntity.getName());
        } else {
            LOGGER.error("function=addVnf; msg=add vnf into DB error.");
            restJson.put("message", "Add vnf into DB error.");
        }
        return restJson;
    }

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since VFC 1.0
     */
    private boolean checkId(String id) {
        VnfEntity vnf = vnfDao.getVnf(id);
        if(null == vnf) {
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
     * @since VFC 1.0
     */
    @Override
    public List<VnfEntity> getList(Map<String, Object> map) throws ServiceException {
        return vnfDao.getVnfs(map);
    }

    /**
     * <br>
     * 
     * @param id
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    @Override
    public int delete(String id) throws ServiceException {
        deleteVnfInfo(id);
        return vnfDao.deleteVnfById(id);
    }

    /**
     * <br>
     * 
     * @param id
     * @throws ServiceException
     * @since VFC 1.0
     */
    private void deleteVnfInfo(String vnfInstanceId) throws ServiceException {
        vnfInfoService.delete(vnfInstanceId);
    }

    public void setVnfDao(VnfDao vnfDao) {
        this.vnfDao = vnfDao;
    }

    public void setVnfInfoService(VnfInfoService vnfInfoService) {
        this.vnfInfoService = vnfInfoService;
    }
}
