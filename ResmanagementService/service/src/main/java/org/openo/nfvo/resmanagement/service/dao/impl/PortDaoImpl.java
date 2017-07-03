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
import org.openo.nfvo.resmanagement.service.dao.inf.PortDao;
import org.openo.nfvo.resmanagement.service.entity.PortEntity;
import org.openo.nfvo.resmanagement.service.mapper.PortMapper;

/**
 *
 * Port DAO implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class PortDaoImpl extends AbstractDao implements PortDao {

    @Override
    public PortEntity getPort(String id) {
        return getMapperManager(PortMapper.class).getPort(id);
    }

    @Override
    public List<PortEntity> getPorts(Map<String, Object> condition) {
        return getMapperManager(PortMapper.class).getPorts(condition);
    }

    @Override
    public int deletePort(String id) {
        return getMapperManager(PortMapper.class).deletePort(id);
    }

    @Override
    public int deletePortByVimId(String vimId) {
        return getMapperManager(PortMapper.class).deletePortByVimId(vimId);
    }

    @Override
    public int addPort(PortEntity portEntity) {
        return getMapperManager(PortMapper.class).addPort(portEntity);
    }

    @Override
    public int addPortSelective(PortEntity portEntity) {
        return getMapperManager(PortMapper.class).addPortSelective(portEntity);
    }

    @Override
    public int updatePortSelective(PortEntity portEntity) {
        return getMapperManager(PortMapper.class).updatePortSelective(portEntity);
    }

    @Override
    public int updatePort(PortEntity portEntity) {
        return getMapperManager(PortMapper.class).updatePort(portEntity);
    }

    @Override
    public int updatePortByVimId(PortEntity portEntity) {
        return getMapperManager(PortMapper.class).updatePortByVimId(portEntity);
    }

}
