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

package org.onap.vfc.nfvo.resmanagement.service.dao.impl;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.dao.inf.VirtualLinkDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.VirtualLinkEntity;
import org.onap.vfc.nfvo.resmanagement.service.mapper.VirtualLinkMapper;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version VFC 1.0 Oct 28, 2016
 */
public class VirtualLinkDaoImpl extends AbstractDao implements VirtualLinkDao {

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since VFC 1.0
     */
    @Override
    public VirtualLinkEntity getVl(String id) {
        return getMapperManager(VirtualLinkMapper.class).selectByPrimaryKey(id);
    }

    /**
     * <br>
     * 
     * @param condition
     * @return
     * @since VFC 1.0
     */
    @Override
    public List<VirtualLinkEntity> getVls(Map<String, Object> condition) {
        return getMapperManager(VirtualLinkMapper.class).getVls(condition);
    }

    /**
     * <br>
     * 
     * @param virtualLinkEntity
     * @return
     * @since VFC 1.0
     */
    @Override
    public int addVl(VirtualLinkEntity virtualLinkEntity) {
        return getMapperManager(VirtualLinkMapper.class).insert(virtualLinkEntity);
    }

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since VFC 1.0
     */
    @Override
    public int deleteVlById(String id) {
        return getMapperManager(VirtualLinkMapper.class).deleteByPrimaryKey(id);
    }

}
