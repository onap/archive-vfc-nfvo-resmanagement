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

package org.openo.nfvo.resmanagement.service.dao.impl;

import java.util.List;
import java.util.Map;

import org.openo.nfvo.resmanagement.service.dao.impl.AbstractDao;
import org.openo.nfvo.resmanagement.service.dao.inf.HostDao;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;
import org.openo.nfvo.resmanagement.service.mapper.HostMapper;

/**
 *
 * Host DAO implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class HostDaoImpl extends AbstractDao implements HostDao {

    @Override
    public HostEntity getHost(String id) {
        return getMapperManager(HostMapper.class).getHost(id);
    }

    @Override
    public List<HostEntity> getHosts(Map<String, Object> condition) {
        return getMapperManager(HostMapper.class).getHosts(condition);
    }

    @Override
    public int deleteHost(String id) {
        return getMapperManager(HostMapper.class).deleteHost(id);
    }

    @Override
    public int deleteHostByVimId(String vimId) {
        return getMapperManager(HostMapper.class).deleteHostByVimId(vimId);
    }

    @Override
    public int addHost(HostEntity hostEntity) {
        return getMapperManager(HostMapper.class).addHost(hostEntity);
    }

    @Override
    public int addHostSelective(HostEntity hostEntity) {
        return getMapperManager(HostMapper.class).addHostSelective(hostEntity);
    }

    @Override
    public int updateHostSelective(HostEntity hostEntity) {
        return getMapperManager(HostMapper.class).updateHostSelective(hostEntity);
    }

    @Override
    public int updateHost(HostEntity hostEntity) {
        return getMapperManager(HostMapper.class).updateHost(hostEntity);
    }

    @Override
    public int updateHostByVimId(HostEntity hostEntity) {
        return getMapperManager(HostMapper.class).updateHostByVimId(hostEntity);
    }

}
