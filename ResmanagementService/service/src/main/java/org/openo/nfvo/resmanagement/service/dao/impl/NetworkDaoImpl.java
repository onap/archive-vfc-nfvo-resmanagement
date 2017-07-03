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
import org.openo.nfvo.resmanagement.service.dao.inf.NetworkDao;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;
import org.openo.nfvo.resmanagement.service.mapper.NetworkMapper;

/**
 *
 * Network DAO implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class NetworkDaoImpl extends AbstractDao implements NetworkDao {

    @Override
    public NetworkEntity getNetwork(String id) {
        return getMapperManager(NetworkMapper.class).getNetwork(id);
    }

    @Override
    public List<NetworkEntity> getNetworks(Map<String, Object> condition) {
        return getMapperManager(NetworkMapper.class).getNetworks(condition);
    }

    @Override
    public int deleteNetwork(String id) {
        return getMapperManager(NetworkMapper.class).deleteNetwork(id);
    }

    @Override
    public int deleteNetworkByVimId(String vimId) {
        return getMapperManager(NetworkMapper.class).deleteNetworkByVimId(vimId);
    }

    @Override
    public int addNetwork(NetworkEntity networkEntity) {
        return getMapperManager(NetworkMapper.class).addNetwork(networkEntity);
    }

    @Override
    public int addNetworkSelective(NetworkEntity networkEntity) {
        return getMapperManager(NetworkMapper.class).addNetworkSelective(networkEntity);
    }

    @Override
    public int updateNetworkSelective(NetworkEntity networkEntity) {
        return getMapperManager(NetworkMapper.class).updateNetworkSelective(networkEntity);
    }

    @Override
    public int updateNetwork(NetworkEntity networkEntity) {
        return getMapperManager(NetworkMapper.class).updateNetwork(networkEntity);
    }

    @Override
    public int updateNetworkByVimId(NetworkEntity networkEntity) {
        return getMapperManager(NetworkMapper.class).updateNetworkByVimId(networkEntity);
    }

}
