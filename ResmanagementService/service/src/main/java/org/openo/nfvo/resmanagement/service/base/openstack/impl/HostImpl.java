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

package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Host;
import org.openo.nfvo.resmanagement.service.business.inf.HostBusiness;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;

import net.sf.json.JSONObject;

/**
 *
 * Host implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class HostImpl implements Host {

    private HostBusiness hostBusiness;

    @Override
    public int add(JSONObject jsonObject) throws ServiceException {
        return hostBusiness.addHost(HostEntity.toEntity(jsonObject));
    }

    @Override
    public int update(JSONObject jsonObject) throws ServiceException {
        return hostBusiness.updateHostSelective(HostEntity.toEntity(jsonObject));
    }

    @Override
    public int update(HostEntity portEntity) throws ServiceException {
        return hostBusiness.updateHostSelective(portEntity);
    }

    @Override
    public int delete(String id) throws ServiceException {
        return hostBusiness.deleteHost(id);
    }

    @Override
    public int updateStatusByVimId(JSONObject jsonObject) throws ServiceException {
        return hostBusiness.updateHostByVimId(HostEntity.toEntity(jsonObject));
    }

    @Override
    public int deleteResByVimId(String vimId) throws ServiceException {
        return hostBusiness.deleteHostByVimId(vimId);
    }

    @Override
    public List<HostEntity> getList(Map<String, Object> condition) throws ServiceException {
        return hostBusiness.getHosts(condition);
    }

    public void setHostBusiness(HostBusiness hostBusiness) {
        this.hostBusiness = hostBusiness;
    }
}
