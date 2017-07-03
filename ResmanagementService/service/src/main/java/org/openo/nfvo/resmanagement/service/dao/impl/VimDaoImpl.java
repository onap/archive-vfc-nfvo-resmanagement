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

import org.openo.nfvo.resmanagement.service.dao.impl.AbstractDao;
import org.openo.nfvo.resmanagement.service.dao.inf.VimDao;
import org.openo.nfvo.resmanagement.service.entity.VimEntity;
import org.openo.nfvo.resmanagement.service.mapper.VimMapper;

/**
 * Vim interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 25, 2016
 */
public class VimDaoImpl extends AbstractDao implements VimDao {

    @Override
    public VimEntity getVim(String id) {
        return getMapperManager(VimMapper.class).getVim(id);
    }

    @Override
    public List<VimEntity> getVims() {
        return getMapperManager(VimMapper.class).getVims();
    }

    @Override
    public int deleteVim(String id) {
        return getMapperManager(VimMapper.class).deleteVim(id);
    }

    @Override
    public int addVim(VimEntity vimEntity) {
        return getMapperManager(VimMapper.class).addVim(vimEntity);
    }
}
