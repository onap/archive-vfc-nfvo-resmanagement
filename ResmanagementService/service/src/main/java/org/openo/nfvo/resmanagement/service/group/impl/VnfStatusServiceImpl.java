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

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.dao.inf.VnfStatusDao;
import org.openo.nfvo.resmanagement.service.entity.VnfStatusEntity;
import org.openo.nfvo.resmanagement.service.group.inf.VnfStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Oct 29, 2016
 */
public class VnfStatusServiceImpl implements VnfStatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VnfStatusServiceImpl.class);

    private VnfStatusDao vnfStatusDao;

    /**
     * <br>
     * 
     * @param object
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @Override
    public JSONObject addVnfStatus(JSONObject object) throws ServiceException {
        LOGGER.info("function=addVnfStatus; object: {}", object);
        VnfStatusEntity vnfStatusEntity = VnfStatusEntity.toEntity(object);
        int result;
        if(!checkId(vnfStatusEntity.getVnfInstanceId())) {
            result = vnfStatusDao.updateVnfStatus(vnfStatusEntity);
        } else {
            result = vnfStatusDao.addVnfStatus(vnfStatusEntity);
        }
        JSONObject resultObj = new JSONObject();
        if(result > 0) {
            resultObj.put("vnfInstanceId", object.get("vnfInstanceId"));
        } else {
            LOGGER.error("function=addVnfStatus; msg=add vnfStatus into DB error.");
            resultObj.put("message", "Add vnfStatus into DB error.");
        }
        return resultObj;
    }

    /**
     * <br>
     * 
     * @param vnfInstanceId
     * @return
     * @since NFVO 0.5
     */
    private boolean checkId(String vnfInstanceId) {
        VnfStatusEntity vnfStatus = vnfStatusDao.getVnfStatus(vnfInstanceId);
        if(null == vnfStatus) {
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
    public List<VnfStatusEntity> getList(Map<String, Object> map) throws ServiceException {
        return vnfStatusDao.getVnfStatuss(map);
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
        return vnfStatusDao.deleteVnfStatusById(id);
    }

    public void setVnfStatusDao(VnfStatusDao vnfStatusDao) {
        this.vnfStatusDao = vnfStatusDao;
    }
}
