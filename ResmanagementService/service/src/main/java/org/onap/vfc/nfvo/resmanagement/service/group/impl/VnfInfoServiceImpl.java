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

import org.onap.vfc.nfvo.resmanagement.common.constant.ParamConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.VnfInfoDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.VmEntity;
import org.onap.vfc.nfvo.resmanagement.service.entity.VnfInfoEntity;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.VmService;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.VnfInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version VFC 1.0 Oct 28, 2016
 */
public class VnfInfoServiceImpl implements VnfInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VnfInfoServiceImpl.class);

    private VnfInfoDao vnfInfoDao;

    private VmService vmService;

    /**
     * <br>
     * 
     * @param object
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    @Override
    public JSONObject addVnfInfo(JSONObject object) throws ServiceException {
        LOGGER.info("function=addVnfInfo; object: {}", object);
        saveVm(object);
        JSONObject vnf = new JSONObject();
        vnf.put(ParamConstant.VNF_INSTANCEID, object.get(ParamConstant.VNF_INSTANCEID));
        vnf.put("nsId", object.get("nsId"));
        vnf.put("vnfmId", object.get("vnfmId"));
        VnfInfoEntity vnfInfoEntity = VnfInfoEntity.toEntity(vnf);
        int result;
        if(!checkId(vnfInfoEntity.getVnfInstanceId())) {
            result = vnfInfoDao.updateVnfInfo(vnfInfoEntity);
        } else {
            result = vnfInfoDao.addVnfInfo(vnfInfoEntity);
        }
        JSONObject resultObj = new JSONObject();
        if(result > 0) {
            resultObj.put(ParamConstant.VNF_INSTANCEID, object.get(ParamConstant.VNF_INSTANCEID));
        } else {
            LOGGER.error("function=addVnfInfo; msg=add vnfInfo into DB error.");
            resultObj.put("message", "Add vnfInfo into DB error.");
        }
        return resultObj;
    }

    /**
     * <br>
     * 
     * @param vnfInstanceId
     * @return
     * @since VFC 1.0
     */
    private boolean checkId(String vnfInstanceId) {
        VnfInfoEntity vnf = vnfInfoDao.getVnfInfo(vnfInstanceId);
        if(null == vnf) {
            return true;
        }
        return false;
    }

    /**
     * <br>
     * 
     * @param object
     * @throws ServiceException
     * @since VFC 1.0
     */
    private void saveVm(JSONObject object) throws ServiceException {
        String vnfInstanceId = object.getString(ParamConstant.VNF_INSTANCEID);
        JSONArray vms = object.getJSONArray("vms");
        for(int i = 0; i < vms.size(); i++) {
            JSONObject vmObj = vms.getJSONObject(i);
            vmObj.put(ParamConstant.VNF_INSTANCEID, vnfInstanceId);
            vmService.addVm(VmEntity.toEntity(vmObj));
        }

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
    public List<VnfInfoEntity> getList(Map<String, Object> map) throws ServiceException {
        return vnfInfoDao.getVnfInfos(map);
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
        vmService.deleteByVnfId(id);
        return vnfInfoDao.deleteVnfInfoById(id);
    }

    public void setVnfInfoDao(VnfInfoDao vnfInfoDao) {
        this.vnfInfoDao = vnfInfoDao;
    }

    public void setVmService(VmService vmService) {
        this.vmService = vmService;
    }
}
