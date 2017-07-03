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

import org.openo.nfvo.resmanagement.service.dao.inf.VmDao;
import org.openo.nfvo.resmanagement.service.entity.VmEntity;
import org.openo.nfvo.resmanagement.service.mapper.VmMapper;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Oct 28, 2016
 */
public class VmDaoImpl extends AbstractDao implements VmDao {

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since NFVO 0.5
     */
    @Override
    public VmEntity getVm(String id) {
        return getMapperManager(VmMapper.class).selectByPrimaryKey(id);
    }

    /**
     * <br>
     * 
     * @param condition
     * @return
     * @since NFVO 0.5
     */
    @Override
    public List<VmEntity> getVms(Map<String, Object> condition) {
        return getMapperManager(VmMapper.class).getVms(condition);
    }

    /**
     * <br>
     * 
     * @param vmEntity
     * @return
     * @since NFVO 0.5
     */
    @Override
    public int addVm(VmEntity vmEntity) {
        return getMapperManager(VmMapper.class).insert(vmEntity);
    }

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since NFVO 0.5
     */
    @Override
    public int deleteVmById(String id) {
        return getMapperManager(VmMapper.class).deleteByPrimaryKey(id);
    }

    /**
     * <br>
     * 
     * @param vmEntity
     * @return
     * @since NFVO 0.5
     */
    @Override
    public int updateVm(VmEntity vmEntity) {
        return getMapperManager(VmMapper.class).updateByPrimaryKeySelective(vmEntity);
    }

    /**
     * <br>
     * 
     * @param vnfInstanceId
     * @return
     * @since NFVO 0.5
     */
    @Override
    public int deleteVmByVnfId(String vnfInstanceId) {
        return getMapperManager(VmMapper.class).deleteByVnfId(vnfInstanceId);
    }

}
