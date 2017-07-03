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

import org.openo.nfvo.resmanagement.service.dao.inf.VnfInfoDao;
import org.openo.nfvo.resmanagement.service.entity.VnfInfoEntity;
import org.openo.nfvo.resmanagement.service.mapper.VnfInfoMapper;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Oct 28, 2016
 */
public class VnfInfoDaoImpl extends AbstractDao implements VnfInfoDao {

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since NFVO 0.5
     */
    @Override
    public VnfInfoEntity getVnfInfo(String id) {
        return getMapperManager(VnfInfoMapper.class).selectByPrimaryKey(id);
    }

    /**
     * <br>
     * 
     * @param condition
     * @return
     * @since NFVO 0.5
     */
    @Override
    public List<VnfInfoEntity> getVnfInfos(Map<String, Object> condition) {
        return getMapperManager(VnfInfoMapper.class).getVnfInfos(condition);
    }

    /**
     * <br>
     * 
     * @param vmEntity
     * @return
     * @since NFVO 0.5
     */
    @Override
    public int addVnfInfo(VnfInfoEntity vnfInfoEntity) {
        return getMapperManager(VnfInfoMapper.class).insert(vnfInfoEntity);
    }

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since NFVO 0.5
     */
    @Override
    public int deleteVnfInfoById(String id) {
        return getMapperManager(VnfInfoMapper.class).deleteByPrimaryKey(id);
    }

    /**
     * <br>
     * 
     * @param vnfInfoEntity
     * @return
     * @since NFVO 0.5
     */
    @Override
    public int updateVnfInfo(VnfInfoEntity vnfInfoEntity) {
        return getMapperManager(VnfInfoMapper.class).updateByPrimaryKeySelective(vnfInfoEntity);
    }

}
