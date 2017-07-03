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

package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Network;
import org.openo.nfvo.resmanagement.service.business.inf.NetworkBusiness;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * Network implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public class NetworkImpl implements Network {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkImpl.class);

    private NetworkBusiness networkBusiness;

    @Override
    public int add(JSONObject jsonObject) throws ServiceException {
        LOGGER.warn("function=addPort; jsonObject={}", jsonObject);
        JSONObject networkObj = dataParse(jsonObject);
        return networkBusiness.addNetwork(NetworkEntity.toEntity(networkObj));
    }

    private JSONObject dataParse(JSONObject jsonObject) {
        JSONObject portObj = new JSONObject();
        portObj.put("id", JsonUtil.getJsonFieldStr(jsonObject, "id"));
        portObj.put("name", JsonUtil.getJsonFieldStr(jsonObject, "name"));
        portObj.put("status", JsonUtil.getJsonFieldStr(jsonObject, "status"));
        portObj.put("tenantId", JsonUtil.getJsonFieldStr(jsonObject, "tenant_id"));
        portObj.put("vimId", JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        portObj.put("vimName", JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        portObj.put("physicalNetwork", JsonUtil.getJsonFieldStr(jsonObject, "physicalNetwork"));
        portObj.put("networkType", JsonUtil.getJsonFieldStr(jsonObject, "networkType"));
        portObj.put("segmentationId", JsonUtil.getJsonFieldStr(jsonObject, "segmentationId"));
        return portObj;
    }

    @Override
    public int add(NetworkEntity entity) throws ServiceException {
        return networkBusiness.addNetwork(entity);
    }

    @Override
    public int update(JSONObject jsonObject) throws ServiceException {
        return networkBusiness.updateNetworkSelective(NetworkEntity.toEntity(jsonObject));
    }

    @Override
    public int delete(String id) throws ServiceException {
        return networkBusiness.deleteNetwork(id);
    }

    @Override
    public int updateStatusByVimId(JSONObject jsonObject) throws ServiceException {
        return networkBusiness.updateNetworkByVimId(NetworkEntity.toEntity(jsonObject));
    }

    @Override
    public List<NetworkEntity> getList(Map<String, Object> condition) throws ServiceException {
        return networkBusiness.getNetworks(condition);
    }

    @Override
    public int deleteResByVimId(String vimId) throws ServiceException {
        return networkBusiness.deleteNetworkByVimId(vimId);
    }

    public void setNetworkBusiness(NetworkBusiness networkBusiness) {
        this.networkBusiness = networkBusiness;
    }

}
